import { Component, Input } from '@angular/core';
import { NgModel } from '@angular/forms';

@Component({
  selector: 'widget-form-item-error',
  templateUrl: './form-item-error.component.html',
  styles: [
  ]
})
export class FormItemErrorComponent {

  @Input()
  control!:NgModel
  @Input()
  message!:string
}
