import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BalanceDetailsComponent } from './balance-details/balance-details.component';
import { BalanceEditComponent } from './balance-edit/balance-edit.component';
import { BalanceListComponent } from './balance-list/balance-list.component';
import { BalanceReportComponent } from './balance-report/balance-report.component';
import { HomeComponent } from './home/home.component';
import { LedgersEditComponent } from './ledgers-edit/ledgers-edit.component';
import { LedgersComponent } from './ledgers/ledgers.component';
import { MemberComponent } from './member.component';

const routes: Routes = [
  { path: '', component: MemberComponent, children: [
    {path: 'home', component: HomeComponent},
    {path: 'ledger', children: [
      {path: 'list', component: LedgersComponent},
      {path: 'edit', component: LedgersEditComponent},
      {path: '', redirectTo: 'list', pathMatch: 'full'}
    ]},
    {path: 'report', component: BalanceReportComponent},
    {path: 'balance/:type', children: [
      {path: 'list', component: BalanceListComponent},
      {path: 'details', component: BalanceDetailsComponent},
      {path: 'edit', component: BalanceEditComponent},
      {path: '', redirectTo: 'list', pathMatch: 'full'}
    ]},
    {path: '', redirectTo: 'home', pathMatch: 'full'}
  ] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MemberRoutingModule { }
