import { Component, Input } from '@angular/core';

@Component({
  selector: 'widget-card',
  templateUrl: './card.component.html',
  styles: [
  ]
})
export class CardComponent {

  @Input()
  title?:string

  @Input()
  bottom?:string
}
