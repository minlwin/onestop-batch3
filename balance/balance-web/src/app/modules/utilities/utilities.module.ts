import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormItemComponent } from './form-item/form-item.component';
import { TopPageComponent } from './top-page/top-page.component';
import { ModalDialogComponent } from './modal-dialog/modal-dialog.component';
import { NoDataComponent } from './no-data/no-data.component';
import { ErrorDialogComponent } from './error-dialog/error-dialog.component';



@NgModule({
  declarations: [
    FormItemComponent,
    TopPageComponent,
    ModalDialogComponent,
    NoDataComponent,
    ErrorDialogComponent,
  ],
  imports: [
    CommonModule
  ],
  exports: [
    FormItemComponent,
    TopPageComponent,
    ModalDialogComponent,
    NoDataComponent,
    ErrorDialogComponent
  ]
})
export class UtilitiesModule { }
