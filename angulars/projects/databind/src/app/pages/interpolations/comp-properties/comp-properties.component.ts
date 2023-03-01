import { Component } from '@angular/core';

@Component({
  templateUrl: './comp-properties.component.html',
  styles: [
  ]
})
export class CompPropertiesComponent {

  message = "Hello Angular Properties Binding"

  array = ["Java Basic", "Spring MVC", "Angular Framework", "Spring Cloud"]

  course = {
    id: 1,
    name: 'Java Basic',
    months: 3,
    fees: 300000
  }

  add = (a:number, b:number) => a + b

}
