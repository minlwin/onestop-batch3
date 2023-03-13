import { Component, Input } from '@angular/core';

@Component({
  selector: 'widget-top-page',
  templateUrl: './top-page.component.html',
  styles: [
  ]
})
export class TopPageComponent {

  @Input()
  icon?:string
  @Input()
  title?:string

  @Input()
  search:boolean = false
}
