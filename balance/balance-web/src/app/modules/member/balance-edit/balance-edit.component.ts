import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BalanceService } from 'src/app/services/balance.service';
import { LedgerService } from 'src/app/services/ledger.service';

@Component({
  templateUrl: './balance-edit.component.html',
  styles: [
  ]
})
export class BalanceEditComponent {

  form:FormGroup
  ledgers:any[] = []

  constructor(
    route:ActivatedRoute,
    private builder:FormBuilder,
    private router:Router,
    private service:BalanceService,
    ledgerService:LedgerService) {

    this.form = builder.group({
      id: 0,
      ledger: ['', Validators.required],
      useDate: ['', Validators.required],
      remark: '',
      items: builder.array([])
    })

    this.addItem()

    route.data.subscribe(data => {
      ledgerService.search(data).subscribe(result => {
        this.ledgers = result
      })
    })
  }

  addItem() {
    this.items.push(this.builder.group({
      id: 0,
      reason: ['', Validators.required],
      unitPrice: [0, Validators.min(1)],
      quentity: [0, Validators.min(1)],
      deleted: false
    }))
  }

  removeItem(index:number) {
    this.items.removeAt(index)

    if(this.items.length == 0) {
      this.addItem()
    }
  }

  save() {
    if(this.form.valid) {
      this.service.save(this.form.value).subscribe(result => {
        this.router.navigate(['/member', 'balence', `${result.ledger.type}`.toLowerCase(), 'details'], {queryParams: {id: result.id}})
      })
    }
  }

  get total() {
    const array = this.items.value as any[]
    return array.map(a => a.unitPrice * a.quentity).reduce((a, b) => a + b)
  }

  get items() {
    return this.form.get('items') as FormArray
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
