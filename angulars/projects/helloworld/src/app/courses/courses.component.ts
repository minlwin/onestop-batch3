import { Component } from '@angular/core';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styles: [
  ]
})
export class CoursesComponent {

  title = "All Courses"

  list = [
    "Java Basic",
    "Spring MVC",
    "Spring Cloud",
    "One Stop"
  ]
}
