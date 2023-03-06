import { Component } from '@angular/core';

@Component({
  templateUrl: './events.component.html',
  styleUrls: [
    './event.component.css'
  ]
})
export class EventsComponent {

  current = '0'
  private lastOpe?:Operator
  private lastValue = 0.0
  private calculated = false

  clear() {
    this.current = '0'
    this.lastOpe = undefined
    this.lastValue = 0.0
    this.calculated = false
  }

  pressNumber(value:string) {
    if(this.current == '0' || this.calculated) {
      this.current = value
    } else {
      this.current += value
    }
    this.calculated = false
  }

  doDecimal() {
    if(!this.current.includes('.')) {
      this.current = `${this.current}.`
    }
  }

  plusOrMinus() {
    if(this.current != '0') {
      if(this.current.startsWith('-')) {
        this.current = this.current.substring(1)
      } else {
        this.current = `-${this.current}`
      }
    }
  }

  doPercent() {
    this.calculate()
    let value = Number.parseFloat(this.current) / 100
    this.current = Number.parseFloat(value.toFixed(9)).toString()
  }

  calculate() {
    if(!this.calculated) {
      this.calculateInternal(this.lastValue, Number.parseFloat(this.current), this.lastOpe)
    }

    this.lastOpe = undefined
    this.lastValue = 0.0
  }

  pressOperator(value:Operator) {
    this.calculateInternal(this.lastValue, Number.parseFloat(this.current), this.lastOpe)
    this.lastOpe = value
  }

  private calculateInternal(digit1:any, digit2:any, ope:any) {
    let value = 0
    switch(ope) {
    case '-':
      value = digit1 - digit2
      break
    case '\u00d7':
      value = digit1 * digit2
      break
    case '\u00f7':
        value = digit1 / digit2
      break
    default:
      value = digit1 + digit2
      break
    }

    this.current = Number.parseFloat(value.toFixed(9)).toString()
    this.lastValue = Number.parseFloat(this.current)
    this.calculated = true
  }
}

declare type Operator = '+' | '-' | '\u00d7' | '\u00f7'
