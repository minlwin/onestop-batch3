import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SecurityUserInfo } from 'src/app/services/security.user.info';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styles: [
  ]
})
export class MemberComponent {

  constructor(private router:Router, private security:SecurityUserInfo) {}

  signOut() {
    this.security.logOut()
    this.router.navigate(['/'])
  }
}
