import { Component, Input } from '@angular/core';

@Component({
  selector: 'widget-input-group',
  templateUrl: './input-group.component.html',
  styles: [
  ]
})
export class InputGroupComponent {

  @Input()
  valid?:boolean
}
