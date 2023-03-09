import { Component, Input } from '@angular/core';

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

  save() {

  }
}
