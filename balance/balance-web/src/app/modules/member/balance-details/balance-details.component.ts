import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  templateUrl: './balance-details.component.html',
  styles: [
  ]
})
export class BalanceDetailsComponent {

  balance:any

  constructor(route:ActivatedRoute) {
    route.queryParamMap.subscribe(result => {
      const id = result.get('id')

      // search balance data with id
    })

    route.data.subscribe(data => {
      this.balance = {
        ledger: data
      }
    })
  }

  get type() {
    return this.balance?.ledger?.type
  }

  get icon() {
    return this.type == 'Debit' ? 'bi-journal-arrow-down' : 'bi-journal-arrow-up'
  }
}
