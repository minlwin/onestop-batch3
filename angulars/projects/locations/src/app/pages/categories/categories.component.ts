import { Component, OnInit, ViewChild } from '@angular/core';
import { CategoryService } from '../../apis/category.service';
import { ModalDialogComponent } from '../../widgets/modal-dialog/modal-dialog.component';

@Component({
  templateUrl: './categories.component.html',
  styles: [
  ]
})
export class CategoriesComponent implements OnInit {

  list:any[] = []

  targetData:any

  @ViewChild(ModalDialogComponent)
  private dialog?:ModalDialogComponent

  constructor(private service:CategoryService) {}

  ngOnInit(): void {
    this.initFormData()
    this.search()
  }

  addNew() {
    this.initFormData()
    this.dialog?.show()
  }

  edit(data:any) {
    const {...form} = data
    this.targetData = form
    this.dialog?.show()
  }

  save(form:any) {
    this.service.save(form).subscribe(_ => {
      this.initFormData()
      this.search()
      this.dialog?.hide()
    })
  }

  private search() {
    this.service.search().subscribe(result => this.list = result)
  }

  private initFormData() {
    this.targetData = {
      id: 0,
      name: '',
      burmese: ''
    }
  }
}
