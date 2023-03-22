import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BalanceService } from 'src/app/services/balance.service';

@Component({
  templateUrl: './balance-details.component.html',
  styles: [
  ]
})
export class BalanceDetailsComponent {

  balance:any

  constructor(route:ActivatedRoute, service:BalanceService) {
    route.queryParamMap.subscribe(result => {
      const id = result.get('id')

      // search balance data with id
      service.findById(id!).subscribe(result => {
        this.balance = result
      })
    })
  }

  get total() {
    if(this.balance) {
      const items = this.balance.items as any[]
      return items.map(a => a.unitPrice * a.quentity).reduce((a, b) => a + b)
    }
    return 0
  }

  get type() {
    return this.balance?.ledger?.type
  }

  get icon() {
    return this.type == 'Debit' ? 'bi-journal-arrow-down' : 'bi-journal-arrow-up'
  }
}
