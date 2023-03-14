import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AccountService } from 'src/app/services/account.service';

@Component({
  templateUrl: './members.component.html',
  styles: [
  ]
})
export class MembersComponent {

  form:FormGroup
  list:any[] = []

  constructor(builder:FormBuilder, private service:AccountService) {
    this.form = builder.group({
      status: '',
      name: ''
    })

    this.search()
  }

  search() {
    this.service.search(this.form.value).subscribe(result => {
      this.list = result
    })
  }
}
