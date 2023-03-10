import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-category-item',
  templateUrl: './category-item.component.html',
  styles: [
  ]
})
export class CategoryItemComponent {

  @Input()
  item:any

  @Output()
  onEdit = new EventEmitter
}
