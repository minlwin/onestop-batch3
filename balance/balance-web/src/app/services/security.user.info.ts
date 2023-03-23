import { Injectable } from "@angular/core"

const LOGIN_USER = 'com.jdc.balance.login.user'
const LOGIN_TOKEN = 'com.jdc.balance.login.token'

@Injectable({providedIn: 'root'})
export class SecurityUserInfo {

  private _token:string | null | undefined
  private _user:any

  constructor() {
    let lastToken = localStorage.getItem(LOGIN_TOKEN)
    if(lastToken) {
      this._token = lastToken
    }

    let lastUser = localStorage.getItem(LOGIN_USER)
    if(lastUser) {
      this._user = JSON.parse(lastUser)
    }
  }

  get securityToken() {
    return this._token
  }

  setToken(token:string | null | undefined) {
    this._token = token

    if(token) {
      localStorage.setItem(LOGIN_TOKEN, token)
    } else {
      this.logOut()
    }
  }

  logOut() {
    this._user = undefined
    this._token = undefined
    localStorage.clear()
  }

  set loginUser(data:any) {
    this._user = data
    localStorage.setItem(LOGIN_USER, JSON.stringify(data))
  }

  get loginUser() {
    return this._user
  }

}
