import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  templateUrl: './constructing.component.html',
  styles: [
  ]
})
export class ConstructingComponent {

  form:FormGroup

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      name: ['', Validators.required],
      contact: builder.group({
        phone: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]]
      }),
      subject: builder.array([
        "One", "Two", "Three", "Four"
      ])
    })
  }
}
