import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { DivisionService } from '../../../apis/division.service';

@Component({
  selector: 'app-township-edit',
  templateUrl: './township-edit.component.html',
  styles: [
  ]
})
export class TownshipEditComponent {

  formData:any = {}

  @Input()
  categories:any[] = []

  divisions:any[] = []

  @Output()
  saveListener = new EventEmitter

  constructor(private service:DivisionService) {}

  changeCategory(category:any) {
    this.formData.category = category
    this.divisions = []

    if(category) {
      this.service.search({type: category}).subscribe(result => {
        this.divisions = result
      })
    }
  }

  changeDivision(division:any) {
    this.formData.division = division
  }

  setFormData(data:any) {
    this.changeCategory(data.category)
    this.formData = data
  }

  save() {
    this.saveListener.emit(this.formData)
  }
}
