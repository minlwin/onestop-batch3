import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AnonymousRoutingModule } from './anonymous-routing.module';
import { AnonymousComponent } from './anonymous.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';


@NgModule({
  declarations: [
    AnonymousComponent,
    SignInComponent,
    SignUpComponent
  ],
  imports: [
    CommonModule,
    AnonymousRoutingModule
  ]
})
export class AnonymousModule { }
