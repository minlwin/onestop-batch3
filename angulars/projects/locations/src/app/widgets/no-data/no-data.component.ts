import { Component, Input } from '@angular/core';

@Component({
  selector: 'widget-no-data',
  templateUrl: './no-data.component.html',
  styles: [
  ]
})
export class NoDataComponent {

  @Input()
  name?:string
}
