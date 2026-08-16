import { Component, AfterViewInit, OnDestroy, NgZone, inject, signal, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RideGroup } from '../../models/ride-group.model';
import * as L from 'leaflet';
import 'leaflet-routing-machine';
import { RideGroupService } from '../../services/ride-group/ride-group-service';

// CDN Leaflet icons to prevent broken image assets
const iconRetinaUrl = 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon-2x.png';
const iconUrl = 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon.png';
const shadowUrl = 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-shadow.png';

const iconDefault = L.icon({
  iconRetinaUrl,
  iconUrl,
  shadowUrl,
  iconSize: [25, 41],
  iconAnchor: [12, 41],
  popupAnchor: [1, -34],
  tooltipAnchor: [16, -28],
  shadowSize: [41, 41]
});
L.Marker.prototype.options.icon = iconDefault;

@Component({
  selector: 'app-ride-groups',
  standalone: true,
  imports: [
    CommonModule, 
    MatButtonModule, 
    MatIconModule, 
    MatToolbarModule
  ],
  templateUrl: './ride-groups.html',
  styleUrl: './ride-groups.scss',
})
export class RideGroups implements AfterViewInit, OnDestroy {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private rideService = inject(RideGroupService);
  private ngZone = inject(NgZone);

  group = signal<RideGroup | null>(null);
  
  private routingControl?: L.Routing.Control;
  private map!: L.Map;
  private destinationMarker!: L.Marker;
  private riderMarker?: L.Marker;

// New state variables for tracking
  isSharing = signal<boolean>(false);
  private gpsWatchId: number | null = null;
  private userMarker?: L.Marker;
  private userName: string = 'Anonymous Rider';

  ngAfterViewInit(): void {
    const groupId = Number(this.route.snapshot.paramMap.get('id'));

    if (groupId) {
      this.rideService.getGroupById(groupId).subscribe({
        next: (data) => {
          this.group.set(data);
          this.initMap(data.destinationLat, data.destinationLong);
        },
        error: () => {
          // Fallback to test coordinates (11.7753, 78.2093) if API fails
          this.useFallbackCoordinates();
        }
      });
    } else {
      this.useFallbackCoordinates();
    }
  }

  private useFallbackCoordinates(): void {
    const fallbackGroup: RideGroup = {
      groupId: 999,
      groupName: 'Test Destination',
      destinationLat: 11.7753,
      destinationLong: 78.2093,
      status: 'ACTIVE'
    };
    this.group.set(fallbackGroup);
    this.initMap(fallbackGroup.destinationLat, fallbackGroup.destinationLong);
  }

  private initMap(lat: number, lng: number): void {
    // Run map initialization outside Angular zone to eliminate drag/zoom lag
    this.ngZone.runOutsideAngular(() => {
      // Clean up existing map instance if re-initializing
      if (this.map) {
        this.map.remove();
      }

      this.map = L.map('rideMap', {
        zoomControl: false,
        preferCanvas: true // HTML5 Canvas rendering for better frame rate
      }).setView([lat, lng], 14);

      // Fast vector tile layer (CartoDB Voyager)
      L.tileLayer('https://{s}.basemaps.cartocdn.com/rastertiles/voyager/{z}/{x}/{y}{r}.png', {
        maxZoom: 19,
        subdomains: 'abcd',
        attribution: '© OpenStreetMap, © CARTO'
      }).addTo(this.map);

      // Set destination marker
      this.destinationMarker = L.marker([lat, lng])
        .addTo(this.map)
        .bindPopup(`<b>${this.group()?.groupName || 'Destination'}</b>`)
        .openPopup();

      // Recalculate container bounds after rendering
      setTimeout(() => {
        this.map.invalidateSize();
      }, 100);
    });
  }

  recenterToDestination(): void {
    const currentGroup = this.group();
    if (currentGroup && this.map) {
      this.ngZone.runOutsideAngular(() => {
        this.map.flyTo(
          [currentGroup.destinationLat, currentGroup.destinationLong], 
          14, 
          { duration: 1.0 }
        );
      });
    }
  }

  // Smooth position update method for live GPS / WebSocket updates
  updateRiderPosition(lat: number, lng: number): void {
    this.ngZone.runOutsideAngular(() => {
      if (this.riderMarker) {
        this.riderMarker.setLatLng([lat, lng]);
      } else if (this.map) {
        this.riderMarker = L.marker([lat, lng]).addTo(this.map);
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/']);
  }

  toggleLocationSharing(): void {
    if (this.isSharing()) {
      this.stopSharing();
    } else {
      this.startSharing();
    }
  }

  private startSharing(): void {
    if (!navigator.geolocation) {
      alert('Geolocation is not supported by your browser.');
      return;
    }

    // Prompt for a name to display on the marker
    const inputName = prompt('Enter your rider name:');
    if (!inputName) return; // Cancel sharing if they don't provide a name
    this.userName = inputName;

    this.isSharing.set(true);

    // Run the continuous GPS tracking outside Angular's zone for max performance
    this.ngZone.runOutsideAngular(() => {
      this.gpsWatchId = navigator.geolocation.watchPosition(
        (position) => {
          const lat = position.coords.latitude;
          const lng = position.coords.longitude;
          
          // 1. Move the user marker
          this.updateUserMarker(lat, lng);
          
          // 2. Update the mapped route to the destination
          this.updateRoute(lat, lng);
        },
        (error) => {
          console.error('GPS tracking error:', error);
          // If tracking fails (e.g., user denies permission), reset UI state
          this.ngZone.run(() => this.isSharing.set(false));
          alert('Failed to get location. Please ensure location permissions are granted.');
        },
        { 
          enableHighAccuracy: true, // Forces GPS chip usage over Wi-Fi triangulation
          maximumAge: 0,            // Do not use cached positions
          timeout: 10000 
        }
      );
    });
  }

  private updateUserMarker(lat: number, lng: number): void {
    if (this.userMarker) {
      // If marker exists, slide it to the new coordinates
      this.userMarker.setLatLng([lat, lng]);
    } else if (this.map) {
      // First time getting a GPS lock: create the marker
      this.userMarker = L.marker([lat, lng])
        .addTo(this.map)
        // Bind a permanent tooltip that hovers above the marker with their name
        .bindTooltip(`<b>${this.userName}</b>`, { 
          permanent: true, 
          direction: 'top', 
          offset: [0, -30],
          className: 'rider-tooltip' // Optional: Custom CSS class for styling
        });

      // Optionally, snap the camera to the user's location on the very first GPS lock
      this.map.flyTo([lat, lng], 15, { duration: 1.5 });
    }
  }

  private stopSharing(): void {
    this.isSharing.set(false);
    
    // Stop polling the hardware GPS
    if (this.gpsWatchId !== null) {
      navigator.geolocation.clearWatch(this.gpsWatchId);
      this.gpsWatchId = null;
    }

    // Remove the marker from the map
    if (this.userMarker && this.map) {
      this.ngZone.runOutsideAngular(() => {
        this.userMarker?.remove();
        this.userMarker = undefined;
      });
    }
    if (this.routingControl && this.map) {
      this.ngZone.runOutsideAngular(() => {
        this.map.removeControl(this.routingControl!);
        this.routingControl = undefined;
      });
    }
  }

  private updateRoute(userLat: number, userLng: number): void {
    const currentGroup = this.group();
    if (!currentGroup || !this.map) return;

    const destLat = currentGroup.destinationLat;
    const destLng = currentGroup.destinationLong;

    this.ngZone.runOutsideAngular(() => {
      if (this.routingControl) {
        // If the route already exists, just update the start point to the new GPS location
        this.routingControl.setWaypoints([
          L.latLng(userLat, userLng),
          L.latLng(destLat, destLng)
        ]);
      } else {
        // First time generating the route
        this.routingControl = L.Routing.control({
          waypoints: [
            L.latLng(userLat, userLng),
            L.latLng(destLat, destLng)
          ],
          show: false, // Hides the bulky text-based turn-by-turn instructions
          addWaypoints: false, // Prevents users from dragging/changing the route
          routeWhileDragging: false,
          fitSelectedRoutes: false, // Set to false so the camera doesn't violently snap on every GPS update
          lineOptions: {
            styles: [{ color: '#3f51b5', opacity: 0.8, weight: 6 }], // Matches Material Primary color
            extendToWaypoints: true,
            missingRouteTolerance: 10
          },
          // Hide default markers since we already have custom ones for User and Destination
          createMarker: () => null 
        } as any ).addTo(this.map);
      }
    });
  }


  ngOnDestroy(): void {
    // Clear GPS tracker
    if (this.gpsWatchId !== null) {
      navigator.geolocation.clearWatch(this.gpsWatchId);
    }
    
    // Clear Map
    if (this.map) {
      this.ngZone.runOutsideAngular(() => {
        this.map.remove();
      });
    }
  }

}