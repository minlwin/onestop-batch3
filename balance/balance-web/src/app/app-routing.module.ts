import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'anonymous', loadChildren: () => import('./modules/anonymous/anonymous.module').then(m => m.AnonymousModule) },
  { path: 'admin', loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule) },
  { path: 'member', loadChildren: () => import('./modules/member/member.module').then(m => m.MemberModule) },
  { path: '', redirectTo: 'anonymous', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
