import { AbstractControl, ValidationErrors } from "@angular/forms";

export const PHONE_PATTERN = '^09\\d([-\\s](\\d){4}){2}$'

export function validatePhone(control:AbstractControl) : ValidationErrors | null {

  if(control.value) {
    const pattern = new RegExp(PHONE_PATTERN)
    if(!pattern.test(control.value)) {
      return {
        'phone' : true
      }
    }
  }

  return null
}
