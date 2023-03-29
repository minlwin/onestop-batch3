import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";

const API = `${environment.baseApi}/security`

@Injectable({providedIn: 'any'})
export class SecurityService {

  constructor(private http:HttpClient) {}

  signIn(form:any) {
    return this.http.post<any>(`${API}/sign-in`, form)
  }

  signUp(form:any) {
    return this.http.post<any>(`${API}/sign-up`, form)
  }
}
