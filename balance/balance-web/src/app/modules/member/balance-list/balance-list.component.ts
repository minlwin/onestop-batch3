import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  templateUrl: './balance-list.component.html',
  styles: [
  ]
})
export class BalanceListComponent {

  form:FormGroup

  constructor(route:ActivatedRoute, builder:FormBuilder) {

    this.form = builder.group({
      type: '',
      ledger: '',
      dateFrom: '',
      dateTo: ''
    })

    route.data.subscribe(data => {
      this.form.patchValue(data)
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
