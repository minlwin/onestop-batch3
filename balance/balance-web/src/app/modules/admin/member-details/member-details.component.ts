import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  templateUrl: './member-details.component.html',
  styles: [
  ]
})
export class MemberDetailsComponent {

  constructor(private router:Router) {}

  updateStatus(status:string) {
    this.router.navigate(['/', 'admin', 'members'])
  }
}
