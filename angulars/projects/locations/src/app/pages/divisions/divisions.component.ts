import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../apis/category.service';
import { DivisionService } from '../../apis/division.service';

@Component({
  templateUrl: './divisions.component.html',
  styles: [
  ]
})
export class DivisionsComponent implements OnInit{

  categories:any[] = []
  list:any[] = []

  constructor(
    private categoryService:CategoryService,
    private divisionService:DivisionService) {}

  ngOnInit(): void {
    this.categoryService.search().subscribe(result => {
      this.categories = result
    })
  }

  search(form:any) {
    this.divisionService.search(form).subscribe(result => {
      this.list = result
    })
  }

  addNew() {

  }

  edit(data:any) {

  }

  save() {

  }
}
