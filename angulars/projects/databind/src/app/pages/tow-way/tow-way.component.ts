import { Component } from '@angular/core';

@Component({
  templateUrl: './tow-way.component.html',
})
export class TowWayComponent {

  subjects = ["Java Basic", "Spring", "Angular", "Flutter"]

  educations = ["BEHS", "Colllege", "Master", "Other"]

  data = {
    name: "Aung Aung",
    phone: "092828272",
    subject: "Java Basic",
    education: "Colllege"
  }
}
