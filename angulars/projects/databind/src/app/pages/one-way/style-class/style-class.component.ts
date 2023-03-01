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
}

declare type Status = 'info' | 'warning' |'error'
