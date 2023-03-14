import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { BalanceService } from 'src/app/services/balance.service';

@Component({
  templateUrl: './balance-report.component.html',
  styles: [
  ]
})
export class BalanceReportComponent {

  form:FormGroup
  list:any[] = []

  constructor(builder:FormBuilder, private service:BalanceService) {
    this.form = builder.group({
      dateFrom: '',
      dateTo: ''
    })
  }

  search() {
    this.service.searchReport(this.form.value).subscribe(result => {
      this.list = result.list
    })
  }
}
