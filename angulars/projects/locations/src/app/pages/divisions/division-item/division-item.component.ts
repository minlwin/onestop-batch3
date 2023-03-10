import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-division-item',
  templateUrl: './division-item.component.html',
  styles: [
  ]
})
export class DivisionItemComponent {

  @Input()
  item:any

  @Output()
  onEdit = new EventEmitter

}
