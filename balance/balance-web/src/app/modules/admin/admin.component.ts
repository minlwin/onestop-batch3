import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styles: [
  ]
})
export class AdminComponent {

  constructor(private router:Router) {

  }

  logOut() {
    this.router.navigate(['/'])
  }
}
