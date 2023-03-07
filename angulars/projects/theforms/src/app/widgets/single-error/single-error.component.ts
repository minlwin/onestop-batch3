import { Component, Input } from '@angular/core';
import { NgModel } from '@angular/forms';

@Component({
  selector: 'widget-single-error',
  templateUrl: './single-error.component.html',
  styles: [
  ]
})
export class SingleErrorComponent {

  @Input()
  message?:string

  @Input()
  textColor?:string

  @Input()
  control!:NgModel
}
