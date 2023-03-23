import { AfterViewInit, Component, ErrorHandler, Inject, ViewChild } from '@angular/core';
import { ErrorDialogComponent } from './modules/utilities/error-dialog/error-dialog.component';
import { CommonErrorHandler } from './services/handlers/common.error.handler';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: []
})
export class AppComponent implements AfterViewInit{

  constructor(@Inject(ErrorHandler) private errorHandler:CommonErrorHandler) {}

  @ViewChild(ErrorDialogComponent)
  errorDialog?:ErrorDialogComponent

  ngAfterViewInit(): void {
    this.errorHandler.dialog = this.errorDialog
  }

}
