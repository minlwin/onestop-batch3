import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_API } from '.';

const API = `${BASE_API}/type`

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http:HttpClient) { }

  search() {
    return this.http.get<any[]>(API)
  }

  findById(id:number) {
    return this.http.get<any>(`${API}/${id}`)
  }

  save(form:any) {
    const {id, ... others} = form
    return (id > 0) ? this.http.put<any>(`${API}/${id}`, others)
      : this.http.post<any>(API, others)
  }
}
