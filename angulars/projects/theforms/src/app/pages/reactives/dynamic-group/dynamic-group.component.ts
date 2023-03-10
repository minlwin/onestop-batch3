import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  templateUrl: './dynamic-group.component.html',
  styles: [
  ]
})
export class DynamicGroupComponent {

  form:FormGroup

  constructor(private builder:FormBuilder) {
    this.form = builder.group({
      name: ['', Validators.required],
      props: builder.array([])
    })

    this.addProps()
  }

  removeProps(index:number) {
    this.props.removeAt(index)

    if(this.props.length == 0) {
      this.addProps()
    }
  }

  addProps() {
    this.props.push(this.builder.group({
      name: ['', Validators.required],
      value: ['', Validators.required]
    }))
  }

  get props() {
    return this.form.get('props') as FormArray
  }
}
