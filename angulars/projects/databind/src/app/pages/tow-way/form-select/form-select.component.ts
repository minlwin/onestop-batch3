import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-form-select',
  templateUrl: './form-select.component.html',
  styles: [
  ]
})
export class FormSelectComponent {

  @Input()
  label?:string

  @Input()
  list?: string[]

  @Input()
  value?:string

  @Output()
  valueChange = new EventEmitter

  changeValue(value:string) {
    this.valueChange.emit(value)
  }
}
