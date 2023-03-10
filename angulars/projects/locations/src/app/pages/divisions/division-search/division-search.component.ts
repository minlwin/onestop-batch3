import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-division-search',
  templateUrl: './division-search.component.html',
  styles: [
  ]
})
export class DivisionSearchComponent {

  @Output()
  onSearch = new EventEmitter

  @Output()
  onAdd = new EventEmitter

  @Input()
  categories:any[] = []

}
