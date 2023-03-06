import { Component, EventEmitter, Input, Output } from '@angular/core';

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

  @Output()
  action = new EventEmitter

  fontSize = '1.2rem'

  radius = '8px'

  mouseOn() {
    this.fontSize = '1.3rem'
    this.radius = '0px'
  }

  mouseOut() {
    this.fontSize = '1.2rem'
    this.radius = '8px'
  }

  pressButton() {
    this.action.emit(this.text)
  }

}
