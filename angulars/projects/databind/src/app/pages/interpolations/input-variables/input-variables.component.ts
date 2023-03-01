import { Component } from '@angular/core';

@Component({
  templateUrl: './input-variables.component.html',
  styles: [
  ]
})
export class InputVariablesComponent {

  list = [
    {id: 1, name: 'Java Basic', months: 3, fees: 300000},
    {id: 2, name: 'Spring MVC', months: 6, fees: 600000},
    {id: 3, name: 'Spring Colud', months: 6, fees: 600000},
    {id: 4, name: 'Angular', months: 4, fees: 400000},
    {id: 5, name: 'Flutter', months: 3, fees: 300000},
  ]
}
