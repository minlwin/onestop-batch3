import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-category-edit',
  templateUrl: './category-edit.component.html',
  styles: [
  ]
})
export class CategoryEditComponent {

  @Input()
  formData:any

  @Output()
  saveListener = new EventEmitter

  save() {
    this.saveListener.emit(this.formData)
  }
}
