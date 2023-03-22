import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormItemComponent } from './form-item/form-item.component';
import { TopPageComponent } from './top-page/top-page.component';
import { ModalDialogComponent } from './modal-dialog/modal-dialog.component';
import { NoDataComponent } from './no-data/no-data.component';



@NgModule({
  declarations: [
    FormItemComponent,
    TopPageComponent,
    ModalDialogComponent,
    NoDataComponent,
  ],
  imports: [
    CommonModule
  ],
  exports: [
    FormItemComponent,
    TopPageComponent,
    ModalDialogComponent,
    NoDataComponent,
  ]
})
export class UtilitiesModule { }
