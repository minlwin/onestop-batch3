import { HttpErrorResponse } from "@angular/common/http";
import { ErrorHandler, Injectable, NgZone } from "@angular/core";
import { ErrorDialogComponent } from "src/app/modules/utilities/error-dialog/error-dialog.component";

@Injectable()
export class CommonErrorHandler implements ErrorHandler {

  dialog?:ErrorDialogComponent

  constructor(private zone:NgZone) {}

  handleError(error: any): void {
    // API Errors
    if(error instanceof HttpErrorResponse) {
      this.zone.run(() => this.dialog?.show(error.error))
    }
  }
}
