import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-cal-button',
  templateUrl: './cal-button.component.html',
  styleUrls: [
    './cal-button.component.css'
  ]
})
export class CalButtonComponent {

  @Input()
  text?:string

  @Input()
  color?:string
}
