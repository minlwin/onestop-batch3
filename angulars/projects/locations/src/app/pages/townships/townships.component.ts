import { Component, OnInit, ViewChild } from '@angular/core';
import { CategoryService } from '../../apis/category.service';
import { TownshipService } from '../../apis/township.service';
import { ModalDialogComponent } from '../../widgets/modal-dialog/modal-dialog.component';
import { TownshipEditComponent } from './township-edit/township-edit.component';

@Component({
  templateUrl: './townships.component.html',
  styles: [
  ]
})
export class TownshipsComponent implements OnInit{

  list:any[] = []

  categories:any[] = []

  @ViewChild(ModalDialogComponent)
  private dialog?:ModalDialogComponent

  @ViewChild(TownshipEditComponent)
  private editForm?:TownshipEditComponent

  constructor(
    private catService:CategoryService,
    private tshService:TownshipService) {
  }

  ngOnInit(): void {
    this.catService.search().subscribe(result => {
      this.categories = result
    })
    this.initForm()
    this.search({})
  }

  search(form:any) {
    this.tshService.search(form).subscribe(result => {
      this.list = result
    })
  }

  addNew() {
    this.initForm()
    this.dialog?.show()
  }

  edit(data:any) {
    const {division, ... form} = data
    form.division = division.id
    form.category = division.type.id
    this.editForm?.setFormData(form)
    this.dialog?.show()
  }

  save(data:any) {
    const {category, ... form} = data
    this.tshService.save(form).subscribe(_ => {
      this.search({})
      this.dialog?.hide()
    })
  }

  private initForm() {
    let form = {
      id: 0,
      name: '',
      burmese: '',
      division: '',
      category: ''
    }

    this.editForm?.setFormData(form)
  }
}
