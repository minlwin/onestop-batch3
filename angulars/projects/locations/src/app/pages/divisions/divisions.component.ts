import { Component, OnInit, ViewChild } from '@angular/core';
import { CategoryService } from '../../apis/category.service';
import { DivisionService } from '../../apis/division.service';
import { ModalDialogComponent } from '../../widgets/modal-dialog/modal-dialog.component';

@Component({
  templateUrl: './divisions.component.html',
  styles: [
  ]
})
export class DivisionsComponent implements OnInit{

  categories:any[] = []

  list:any[] = []

  targetData:any

  @ViewChild(ModalDialogComponent)
  dialog?:ModalDialogComponent

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
    this.initFormData()
    this.dialog?.show()
  }

  edit(data:any) {
    const {... formData} = data
    this.targetData = formData
    this.dialog?.show()
  }

  save() {

  }

  private initFormData() {
    this.targetData = {
      name: '',
      burmese: '',
      capital: '',
      type: ''
    }
  }
}
