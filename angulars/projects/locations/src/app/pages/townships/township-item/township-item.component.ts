import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-township-item',
  templateUrl: './township-item.component.html',
  styles: [
  ]
})
export class TownshipItemComponent {

  @Input()
  item:any

  @Output()
  onEdit = new EventEmitter

}
