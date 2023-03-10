import { Component, Input } from '@angular/core';

@Component({
  selector: 'widget-top-page',
  templateUrl: './top-page.component.html',
  styles: [
  ]
})
export class TopPageComponent {

  @Input()
  title?:string

  @Input()
  icon?:string
}
