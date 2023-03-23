import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SecurityUserInfo } from 'src/app/services/security.user.info';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styles: [
  ]
})
export class AdminComponent {

  constructor(private router:Router, private security:SecurityUserInfo) {

  }

  logOut() {
    this.security.logOut()
    this.router.navigate(['/'])
  }
}
