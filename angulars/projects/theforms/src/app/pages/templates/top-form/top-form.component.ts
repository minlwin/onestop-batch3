import { Component } from '@angular/core';

@Component({
  templateUrl: './top-form.component.html',
  styles: [
  ]
})
export class TopFormComponent {

  submittedData:any

  saveData(data:any) {
    this.submittedData = data
  }
}
