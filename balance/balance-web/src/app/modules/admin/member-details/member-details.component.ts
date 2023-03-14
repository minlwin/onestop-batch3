import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from 'src/app/services/account.service';

@Component({
  templateUrl: './member-details.component.html',
  styles: [
  ]
})
export class MemberDetailsComponent {

  dto:any

  constructor(route:ActivatedRoute, private router:Router, private service:AccountService) {
    route.queryParamMap.subscribe(params => {
      service.findById(params.get('id')!).subscribe(result => {
        this.dto = result
      })
    })
  }

  updateStatus(status:string) {
    const form = {
      id: this.dto.id,
      status: status
    }

    this.service.updateStatus(form).subscribe(_ => {
      this.router.navigate(['/', 'admin', 'members'])
    })
  }
}
