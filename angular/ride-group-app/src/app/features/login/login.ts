import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {

  username = '';
  private authService = inject(AuthService);
  private router = inject(Router);

  onSubmit(): void {
    if (this.username.trim()) {
      this.authService.login(this.username.trim());
      this.router.navigate(['']);
    }
  }
}
