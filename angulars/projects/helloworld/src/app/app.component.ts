import { AfterContentChecked, AfterContentInit, AfterViewChecked, AfterViewInit, Component, DoCheck, OnChanges, OnDestroy, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { CounterComponent } from './counter/counter.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit, OnChanges, DoCheck,
AfterContentInit, AfterContentChecked,
AfterViewInit, AfterViewChecked, OnDestroy{
  title = 'Hello Angular';

  name = 'App Component'

  @ViewChild(CounterComponent)
  counter?:CounterComponent

  constructor() {
    console.log(`Constructor Call ${this.name}`)
  }

  ngOnInit(): void {
    console.log(`ngOnInit Call ${this.name}`)
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(`ngOnChanges Call ${this.name}`)
  }

  ngDoCheck(): void {
    console.log(`ngDoCheck Call ${this.name}`)
  }

  ngAfterContentInit(): void {
    console.log(`ngAfterContentInit Call ${this.name}`)
  }

  ngAfterContentChecked(): void {
    console.log(`ngAfterContentChecked Call ${this.name}`)
  }

  ngAfterViewInit(): void {
    console.log(`ngAfterViewInit Call ${this.name}`)
  }

  ngAfterViewChecked(): void {
    console.log(`ngAfterViewChecked Call ${this.name}`)
  }

  ngOnDestroy(): void {
    console.log(`ngOnDestroy Call ${this.name}`)
  }
}
