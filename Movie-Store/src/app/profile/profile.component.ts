import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { DataService } from '../data.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent {
  constructor(
    private dataService: DataService,
    private authService: AuthService
  ) {}

  username = 'Sanket';
  onInit() {
    this.username = this.authService.getUsername();
  }

  logout() {
    window.sessionStorage.removeItem('auth-user');
  }
}
