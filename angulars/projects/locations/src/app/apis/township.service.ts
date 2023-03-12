import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BASE_API } from ".";

const API = `${BASE_API}/township`

@Injectable({providedIn: 'root'})
export class TownshipService {

  constructor(private http:HttpClient) {}

  search(form:any) {
    return this.http.get<any[]>(API, {params: form})
  }

  findById(id: number) {
    return this.http.get<any>(`${API}/${id}`)
  }

  save(form:any) {
    const {id, ... data} = form
    return id ? this.http.put<any>(`${API}/${id}`, data)
      : this.http.post<any>(API, data)
  }
}
