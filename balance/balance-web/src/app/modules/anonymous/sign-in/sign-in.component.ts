import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SecurityService } from 'src/app/services/api/security.service';
import { SecurityUserInfo } from 'src/app/services/security.user.info';

@Component({
  templateUrl: './sign-in.component.html',
  styles: [
  ]
})
export class SignInComponent {

  form:FormGroup

  constructor(
    builder:FormBuilder,
    private router:Router,
    private security:SecurityService,
    private securityContext:SecurityUserInfo) {
    this.form = builder.group({
      loginId: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  signIn() {
    if(this.form.valid) {
      this.security.signIn(this.form.value).subscribe(result => {
        // Save Login Result to Share ammong modules
        this.securityContext.loginUser = result
        // Navigate their homes
        this.router.navigate([result?.role?.toString().toLowerCase() || 'anonymous'])
      })
    }
  }
}
