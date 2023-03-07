import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-form-input',
  templateUrl: './form-input.component.html',
  styles: [
  ]
})
export class FormInputComponent {

  @Input()
  label?:string

  @Input()
  type?:string

  @Input()
  value?:string

  @Output()
  valueChange = new EventEmitter

  changeValue(value:string) {
    this.valueChange.emit(value)
  }
}
