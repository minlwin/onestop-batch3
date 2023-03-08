import { AfterViewInit, Component, OnInit } from '@angular/core';
import { CategoryService } from '../../apis/category.service';

declare const bootstrap:any

@Component({
  templateUrl: './categories.component.html',
  styles: [
  ]
})
export class CategoriesComponent implements OnInit, AfterViewInit{

  list:any[] = []

  private modal:any

  constructor(private service:CategoryService) {}

  ngOnInit(): void {
    this.service.search().subscribe(result => this.list = result)
  }

  ngAfterViewInit(): void {
    this.modal = new bootstrap.Modal('#categoryEditModal', {})
  }

  addNew() {
    this.modal.show()
  }

  edit(data:any) {

  }
}
