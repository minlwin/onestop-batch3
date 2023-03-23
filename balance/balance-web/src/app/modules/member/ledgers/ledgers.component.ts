import { Component, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LedgerService } from 'src/app/services/ledger.service';
import { ModalDialogComponent } from '../../utilities/modal-dialog/modal-dialog.component';

@Component({
  templateUrl: './ledgers.component.html',
  styles: [
  ]
})
export class LedgersComponent {

  list:any[] = []
  form:FormGroup
  searchForm:FormGroup

  @ViewChild(ModalDialogComponent)
  dialog?:ModalDialogComponent

  constructor(builder:FormBuilder, private service:LedgerService) {

    this.searchForm = builder.group({
      type: ''
    })

    this.form = builder.group({
      id: 0,
      type: ['', Validators.required],
      name: ['', Validators.required]
    })

    this.search()
  }

  search() {
    this.service.search(this.searchForm.value).subscribe(result => this.list = result)
  }

  upload(file:FileList | null) {
    if(file && file.length > 0) {
      this.service.upload(file[0]).subscribe(result => {
        this.search()
      })
    }
  }

  addNew() {
    this.form.patchValue({
      id: 0,
      type: '',
      name: ''
    })
    this.dialog?.show()
  }

  edit(item:any) {
    this.form.patchValue(item)
    this.dialog?.show()
  }

  save() {
    if(this.form.valid) {
      this.service.save(this.form.value).subscribe(_ => {
        this.dialog?.hide()
        this.search()
      })
    }
  }
}
