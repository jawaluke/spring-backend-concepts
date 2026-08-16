import { Component, inject, signal } from '@angular/core';
import { AuthService } from '../../services/auth-service';
import { Router } from '@angular/router';
import { RideGroupService } from '../../services/ride-group/ride-group-service';
import { RideGroup } from '../../models/ride-group.model';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatRippleModule } from '@angular/material/core';

@Component({
  selector: 'app-home',
  imports: [CommonModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatToolbarModule,
    MatRippleModule],
  templateUrl: './home.html',
  styleUrl: './home.scss',
})
export class Home {
  protected authService = inject(AuthService);
  private router = inject(Router);

  // Read-only signal for the template
  protected isAuthenticated = this.authService.isAuthenticated();

  private rideGroupService = inject(RideGroupService);

  // Signal holding the groups
  groups = signal<RideGroup[]>([]);

  ngOnInit(): void {
    this.rideGroupService.getActiveGroups().subscribe({
      next: (data) => this.groups.set(data),
      error: (err) => console.error('Failed to load groups', err)
    });
  }

  navigateToGroup(groupId: number): void {
    // Navigates to the map view page: /group/1
    this.router.navigate(['/group', groupId]);
  }

  createRide(): void {
    // Optional: Navigate to a creation form
    console.log('Navigate to create ride page');
  }

  navigateToLogin() {
    this.router.navigate(['/login']);
  }

}
