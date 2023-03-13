import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormItemComponent } from './form-item/form-item.component';
import { TopPageComponent } from './top-page/top-page.component';
import { ModalDialogComponent } from './modal-dialog/modal-dialog.component';



@NgModule({
  declarations: [
    FormItemComponent,
    TopPageComponent,
    ModalDialogComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    FormItemComponent,
    TopPageComponent,
    ModalDialogComponent
  ]
})
export class UtilitiesModule { }
