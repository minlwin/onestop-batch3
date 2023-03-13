import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormItemComponent } from './form-item/form-item.component';
import { TopPageComponent } from './top-page/top-page.component';



@NgModule({
  declarations: [
    FormItemComponent,
    TopPageComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    FormItemComponent,
    TopPageComponent
  ]
})
export class UtilitiesModule { }
