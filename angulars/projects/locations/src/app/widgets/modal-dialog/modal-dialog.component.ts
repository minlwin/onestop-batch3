import { AfterViewInit, Component, Input } from '@angular/core';

declare const bootstrap:any

@Component({
  selector: 'widget-modal-dialog',
  templateUrl: './modal-dialog.component.html',
  styles: [
  ]
})
export class ModalDialogComponent implements AfterViewInit{

  @Input('modalId')
  id!:string
  @Input()
  title?:string

  private dialog:any

  ngAfterViewInit(): void {
    this.dialog = new bootstrap.Modal(`#${this.id}`)
  }

  show() {
    this.dialog.show()
  }

  hide() {
    this.dialog.hide()
  }

}
