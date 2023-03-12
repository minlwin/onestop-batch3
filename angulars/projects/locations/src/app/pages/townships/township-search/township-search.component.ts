import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { DivisionService } from '../../../apis/division.service';

@Component({
  selector: 'app-township-search',
  templateUrl: './township-search.component.html',
  styles: [
  ]
})
export class TownshipSearchComponent {

  @Output()
  onSearch = new EventEmitter

  @Output()
  onAdd = new EventEmitter

  @Input()
  categories:any[] = []

  divisions:any[] = []

  constructor(private service:DivisionService) {}

  changeCategory(category:any) {
    this.divisions = []
    if(category) {
      this.service.search({type: category}).subscribe(result => {
        this.divisions = result
      })
    }
  }
}
