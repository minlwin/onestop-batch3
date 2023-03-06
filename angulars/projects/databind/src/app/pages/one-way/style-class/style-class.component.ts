import { Component } from '@angular/core';

@Component({
  templateUrl: './style-class.component.html',
  styles: [
  ]
})
export class StyleClassComponent {

  status: Status = 'info'

  cardBodyClass = 'card-body bg-info'

  setStatus(value: Status) {
    this.status = value
  }

  bgColor = 'blue'


  divStyle = {
    "border-color" : 'red',
    "border-style" : 'dashed',
    "border-width" : '2px',
    "border-radius" : '16px'
  }

  switchBackgroundColor() {
    this.bgColor = this.bgColor == 'blue' ? 'red' : 'blue'
  }
}

declare type Status = 'info' | 'warning' |'error'
