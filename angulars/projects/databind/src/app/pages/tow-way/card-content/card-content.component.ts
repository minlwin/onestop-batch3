import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-card-content',
  templateUrl: './card-content.component.html',
  styles: [
  ]
})
export class CardContentComponent {

  @Input()
  title?:string
}
