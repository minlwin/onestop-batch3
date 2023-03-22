import { Injectable } from "@angular/core"

@Injectable({providedIn: 'root'})
export class SecurityUserInfo {

  private _token:string | null | undefined
  loginUser:any

  get securityToken() {
    return this._token
  }

  setToken(token:string | null | undefined) {
    this._token = token

    if(!token) {
      this.loginUser = null
    }
  }

}
