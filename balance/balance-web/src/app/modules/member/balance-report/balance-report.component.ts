import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { BalanceService } from 'src/app/services/api/balance.service';

@Component({
  templateUrl: './balance-report.component.html',
  styles: [
  ]
})
export class BalanceReportComponent {

  form:FormGroup
  dto:any

  constructor(builder:FormBuilder, private service:BalanceService) {
    this.form = builder.group({
      dateFrom: '',
      dateTo: ''
    })
  }

  search() {
    this.service.searchReport(this.form.value).subscribe(result => {
      this.dto = result
    })
  }

  get list():any[] {
    return this.dto?.list || []
  }
}
