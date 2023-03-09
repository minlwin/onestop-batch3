import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BASE_API } from ".";

const API = `${BASE_API}/division`

@Injectable({providedIn: 'root'})
export class DivisionService {

  constructor(private http:HttpClient) {}

  search(form:any) {
    return this.http.get<any[]>(API, {params: form})
  }

  findById(id:number) {
    return this.http.get<any>(`${API}/${id}`)
  }

  save(form:any) {
    const {id, ... data} = form
    return id == 0 ? this.http.post<any>(API, data)
      : this.http.put<any>(`${API}/${id}`, data)
  }
}
