import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  templateUrl: './sign-in.component.html',
  styles: [
  ]
})
export class SignInComponent {

  form:FormGroup

  constructor(builder:FormBuilder, private router:Router) {
    this.form = builder.group({
      loginId: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  signIn() {
    this.router.navigate(["/", this.form.get('loginId')?.value == 'Admin' ? 'admin' : 'member'])
  }
}
