import { Component } from '@angular/core';

@Component({
  templateUrl: './group-form.component.html',
  styles: [
  ]
})
export class GroupFormComponent {

  list:any[] = []

  addNew(item:any) {
    this.list.push(item)
  }
}
