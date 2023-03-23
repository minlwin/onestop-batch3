import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BASE_API } from "..";

const API = `${BASE_API}/ledger`

@Injectable({providedIn: 'any'})
export class LedgerService {

  constructor(private http:HttpClient) {}

  search(form:any) {
    return this.http.get<any[]>(API, {params: form})
  }

  save(form: any) {
    return form.id ? this.http.put<any>(API, form)
      : this.http.post<any>(API, form);
  }

  upload(file: File) {
    const form = new FormData
    form.append("file", file)
    return this.http.post<any>(`${API}/upload`, form)
  }
}
