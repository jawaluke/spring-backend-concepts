import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule
  ],
  templateUrl: './header.html',
  styleUrl: './header.scss',
})
export class Header {

  applicationName = "Ride_Group_App"

  private router = inject(Router);

  navigateToLop(): void {
    this.router.navigate(['/profile']);
  }

  goToHome(): void {
    this.router.navigate(['']);
  }

}
