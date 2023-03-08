import { Component } from '@angular/core';
import { Messages } from '../../../widgets/multi-error/multi-error.component';

@Component({
  templateUrl: './template-data-bind.component.html',
  styles: [
  ]
})
export class TemplateDataBindComponent {

  user = new User

  list:User[] = []

  emailValidationError:Messages = {
    required : 'Please Enter Email',
    email: 'Please enter valid email',
  }

  phoneValidationError:Messages = {
    required : 'Please enter phone number',
    phone: 'Please enter valid phone number'
  }

  addNew() {
    this.list.push(this.user)
    this.user = new User
  }
}

export class User {
  name:string = ''
  contact:Contact = new Contact
}

export class Contact {
  phone:string = ''
  email:string = ''
}
