import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
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
    this.initFormData()
    this.categoryService.search().subscribe(result => {
      this.categories = result
      this.search({})
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
    const {type, ... formData} = data
    formData.type = type.id
    this.targetData = formData
    this.dialog?.show()
  }

  save(data:any) {
    this.divisionService.save(data).subscribe(_ => {
      this.search({})
      this.initFormData()
      this.dialog?.hide()
    })
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
