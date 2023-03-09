import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-division-edit',
  templateUrl: './division-edit.component.html',
  styles: [
  ]
})
export class DivisionEditComponent {

  @Input()
  formData:any

  @Input()
  categories:any[] = []

  @Output()
  saveListener = new EventEmitter

  save() {
    this.saveListener.emit(this.formData)
  }

  setType(value:any) {
    this.formData.type = value
  }
}
