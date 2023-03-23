import { AfterViewInit, Component } from '@angular/core';

declare const bootstrap:any

@Component({
  selector: 'app-error-dialog',
  templateUrl: './error-dialog.component.html',
  styles: [
  ]
})
export class ErrorDialogComponent implements AfterViewInit{

  type?:string

  messages:string[] = []

  private dialog?:any

  ngAfterViewInit(): void {
    this.dialog = new bootstrap.Modal('#commonErrorDialog')
  }

  show(error:any) {
    this.type = error?.type
    this.messages = error?.messages
    this.dialog?.show()
  }

  close() {
    this.dialog?.hide()
  }

}
