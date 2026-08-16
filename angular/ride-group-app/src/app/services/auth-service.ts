import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  currentUser = signal<string | null>(localStorage.getItem('username'));

  login(username: string): void {
    localStorage.setItem('username', username);
    this.currentUser.set(username);
  }

  logout(): void {
    localStorage.removeItem('username');
    this.currentUser.set(null);
  }

  isAuthenticated(): boolean {
    return this.currentUser() !== null && this.currentUser()?.trim() !== '';
  }
}
