import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AnonymousRoutingModule } from './anonymous-routing.module';
import { AnonymousComponent } from './anonymous.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ReactiveFormsModule } from '@angular/forms';
import { UtilitiesModule } from '../utilities/utilities.module';

@NgModule({
  declarations: [
    AnonymousComponent,
    SignInComponent,
    SignUpComponent
  ],
  imports: [
    CommonModule,
    AnonymousRoutingModule,
    ReactiveFormsModule,
    UtilitiesModule
  ]
})
export class AnonymousModule { }
