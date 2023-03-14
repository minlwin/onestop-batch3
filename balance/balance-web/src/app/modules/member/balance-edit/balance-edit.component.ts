import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  templateUrl: './balance-edit.component.html',
  styles: [
  ]
})
export class BalanceEditComponent {

  form:FormGroup

  constructor(route:ActivatedRoute, builder:FormBuilder) {

    this.form = builder.group({
      id: 0,
      type: '',
      ledger: '',
    })

    route.data.subscribe(data => {
      this.form.patchValue(data)
    })
  }

  get title() {
    return `${this.form.get('id')?.value ? 'Edit' : 'Add New'} ${this.type}`
  }

  get icon() {
    return this.form.get('id')?.value ? 'bi-pencil' : 'bi-plus-lg'
  }

  get type():string {
    return this.form.get('type')?.value
  }
}
