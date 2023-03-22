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
  type:any

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

      this.type = data['type']

      ledgerService.search(data).subscribe(result => {
        this.ledgers = result
      })
    })

    route.queryParamMap.subscribe(params => {
      let id = params.get('id')
      if(id) {
        service.findById(id).subscribe(result => {
          const {items, ledger, ... data} = result
          this.form.patchValue(data)
          this.form.get('ledger')?.setValue(ledger.id)

          const balanceItems = items as any[]

          this.items.clear()
          for(let item of balanceItems) {
            this.setItem(item)
          }
        })
      }
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

  setItem(item:any) {
    this.items.push(this.builder.group({
      id: item.id,
      reason: [item.reason, Validators.required],
      unitPrice: [item.unitPrice, Validators.min(1)],
      quentity: [item.quentity, Validators.min(1)],
      deleted: false
    }))
  }

  canDisplay(index:number) {
    const target = this.items.controls.at(index)

    if(target) {
      const item = target as FormGroup
      return !item.get('deleted')?.value
    }
    return true
  }

  removeItem(index:number) {
    const target = this.items.controls.at(index)

    if(target) {
      const item = target as FormGroup
      item.patchValue({deleted : true})
    }

    const array = this.items.value as any[]
    if(array.filter(data => !data.deleted).length = 0) {
      this.addItem()
    }
  }

  save() {
    if(this.form.valid) {
      this.service.save(this.form.value).subscribe(result => {
        this.router.navigate(['/member', 'balance', `${result.ledger.type}`.toLowerCase(), 'details'], {queryParams: {id: result.id}})
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

}
