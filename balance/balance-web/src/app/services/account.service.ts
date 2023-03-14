import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BASE_API } from ".";

const API = `${BASE_API}/account`

@Injectable({providedIn: 'any'})
export class AccountService {


  constructor(private http:HttpClient) {}

  search(form: any) {
    return this.http.get<any[]>(API, {params: form})
  }

  findById(id: string) {
    return this.http.get<any>(`${API}/${id}`)
  }

  updateStatus(form: any) {
    return this.http.put<any>(`${API}/status`, form)
  }
}
