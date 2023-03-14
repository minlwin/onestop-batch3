import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { BalanceService } from 'src/app/services/balance.service';
import { LedgerService } from 'src/app/services/ledger.service';
import { LedgersComponent } from '../ledgers/ledgers.component';

@Component({
  templateUrl: './balance-list.component.html',
  styles: [
  ]
})
export class BalanceListComponent {

  form:FormGroup
  list:any[] = []
  ledgers:any[] = []

  constructor(
    route:ActivatedRoute,
    builder:FormBuilder,
    private service:BalanceService,
    ledgerService:LedgerService) {

    this.form = builder.group({
      type: '',
      ledger: '',
      dateFrom: '',
      dateTo: ''
    })

    route.data.subscribe(data => {
      this.form.patchValue(data)
    })

    ledgerService.search({type: this.type}).subscribe(result => {
      this.ledgers = result
    })
  }

  search() {
    this.service.search(this.form.value).subscribe(result => {
      this.list = result
    })
  }

  get title() {
    return `${this.type} Management`
  }

  get icon() {
    return this.type == 'Debit' ? 'bi-journal-arrow-down' : 'bi-journal-arrow-up'
  }

  get type():string {
    return this.form.get('type')?.value
  }
}
