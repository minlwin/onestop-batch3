import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { MembersComponent } from './members/members.component';
import { MemberDetailsComponent } from './member-details/member-details.component';
import { UtilitiesModule } from '../utilities/utilities.module';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AdminComponent,
    MembersComponent,
    MemberDetailsComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    UtilitiesModule,
    ReactiveFormsModule
  ]
})
export class AdminModule { }
