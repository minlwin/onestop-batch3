import { Component } from '@angular/core';
import { Messages } from '../../../widgets/multi-error/multi-error.component';

@Component({
  templateUrl: './group-form.component.html',
  styles: [
  ]
})
export class GroupFormComponent {

  list:any[] = []

  emailValidationError:Messages = {
    required : 'Please Enter Email',
    email: 'Please enter valid email',
  }

  phoneValidationError:Messages = {
    required : 'Please enter phone number',
    phone: 'Please enter valid phone number'
  }

  addNew(item:any) {
    this.list.push(item)
  }
}
