import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styles: [
  ]
})
export class MemberComponent {

  constructor(private router:Router) {}

  signOut() {
    this.router.navigate(['/'])
  }
}
