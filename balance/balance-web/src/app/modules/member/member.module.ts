import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MemberRoutingModule } from './member-routing.module';
import { MemberComponent } from './member.component';
import { HomeComponent } from './home/home.component';
import { BalanceReportComponent } from './balance-report/balance-report.component';
import { BalanceListComponent } from './balance-list/balance-list.component';
import { BalanceEditComponent } from './balance-edit/balance-edit.component';
import { BalanceDetailsComponent } from './balance-details/balance-details.component';
import { LedgersComponent } from './ledgers/ledgers.component';
import { LedgersEditComponent } from './ledgers-edit/ledgers-edit.component';
import { UtilitiesModule } from '../utilities/utilities.module';


@NgModule({
  declarations: [
    MemberComponent,
    HomeComponent,
    BalanceReportComponent,
    BalanceListComponent,
    BalanceEditComponent,
    BalanceDetailsComponent,
    LedgersComponent,
    LedgersEditComponent
  ],
  imports: [
    CommonModule,
    MemberRoutingModule,
    UtilitiesModule
  ]
})
export class MemberModule { }
