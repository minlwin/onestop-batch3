import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, tap } from "rxjs";
import { SecurityUserInfo } from "./security.user.info";

const SECURITY_TOKEN = 'jdc-web-token'

@Injectable()
export class SecurityTokenInterceptor implements HttpInterceptor{

  constructor(private userStorage:SecurityUserInfo) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let cloneRequest = req

    if(this.userStorage.securityToken) {
      cloneRequest = req.clone({headers: req.headers.append(SECURITY_TOKEN, this.userStorage.securityToken)})
    }

    return next.handle(cloneRequest).pipe(
      tap(e => {
        if(e instanceof HttpResponse) {
          let token = e.headers.get(SECURITY_TOKEN)
          this.userStorage.setToken(token)
        }
      })
    )
  }

}
