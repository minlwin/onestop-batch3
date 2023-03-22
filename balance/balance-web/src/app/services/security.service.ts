import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BASE_API } from ".";

const API = `${BASE_API}/security`

@Injectable({providedIn: 'any'})
export class SecurityService {

  constructor(private http:HttpClient) {}

  signIn(form:any) {
    return this.http.post<any>(`${API}/sign-in`, form)
  }

}
