import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BASE_API } from ".";

const API = `${BASE_API}/balance`

@Injectable({providedIn: 'any'})
export class BalanceService {

  constructor(private http:HttpClient) {}

  search(form: any) {
    return this.http.get<any[]>(API, {params: form})
  }

  searchReport(form: any) {
    return this.http.get<any>(`${API}/report`, {params: form})
  }

  findById(id: string) {
    return this.http.get<any>(`${API}/${id}`)
  }

  save(form: any) {
    return form.id ? this.http.put<any>(API, form) :
      this.http.post<any>(API, form)
  }
}
