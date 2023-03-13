import { Component, ViewChild } from '@angular/core';
import { ModalDialogComponent } from '../../utilities/modal-dialog/modal-dialog.component';

@Component({
  templateUrl: './ledgers.component.html',
  styles: [
  ]
})
export class LedgersComponent {

  list:any[] = [1, 2, 3, 4, 5]

  @ViewChild(ModalDialogComponent)
  dialog?:ModalDialogComponent

  upload(file:FileList | null) {
    console.log(file)
  }

  addNew() {
    this.dialog?.show()
  }
}
