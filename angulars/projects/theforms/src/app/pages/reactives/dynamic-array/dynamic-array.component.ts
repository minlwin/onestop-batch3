import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  templateUrl: './dynamic-array.component.html',
  styles: [
  ]
})
export class DynamicArrayComponent {

  form:FormGroup

  constructor(private builder:FormBuilder) {
    this.form = builder.group({
      name: ['', Validators.required],
      subjects: builder.array([])
    })

    this.addSubject()
  }

  addSubject() {
    this.subjects.push(this.builder.control('', Validators.required))
  }

  removeSubject(index:number) {
    this.subjects.removeAt(index)

    if(this.subjects.length == 0) {
      this.addSubject()
    }
  }

  get subjects():FormArray {
    return this.form.get('subjects') as FormArray
  }
}
