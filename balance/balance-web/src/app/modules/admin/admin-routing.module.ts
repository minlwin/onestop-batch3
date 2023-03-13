import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { MemberDetailsComponent } from './member-details/member-details.component';
import { MembersComponent } from './members/members.component';

const routes: Routes = [{ path: '', component: AdminComponent, children: [
  {path: 'members', component: MembersComponent},
  {path: 'member-details', component: MemberDetailsComponent},
  {path: '', redirectTo: 'members', pathMatch: 'full'}
] }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
