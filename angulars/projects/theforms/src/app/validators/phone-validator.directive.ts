import { Directive } from '@angular/core';
import { AbstractControl, NG_VALIDATORS, ValidationErrors, Validator } from '@angular/forms';

@Directive({
  selector: '[phone]',
  providers: [
    {provide: NG_VALIDATORS, useExisting: PhoneValidatorDirective, multi: true}
  ]
})
export class PhoneValidatorDirective implements Validator{

  private pattern = new RegExp('^09\\d([-\\s](\\d){4}){2}$')

  constructor() { }

  validate(control: AbstractControl<any, any>): ValidationErrors | null {

    if(control.value && !this.pattern.test(control.value)) {
      return { phone : true }
    }

    return null
  }

}
