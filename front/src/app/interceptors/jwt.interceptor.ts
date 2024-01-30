import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { SessionService } from "../services/session.service";

@Injectable({ providedIn: 'root' })
export class JwtInterceptor implements HttpInterceptor {

    constructor (private sessionService: SessionService) { }
    
    public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (this.sessionService.isLogged) {
            /*req = req.clone({
                setHeaders: {
                    Authorization: `Bearer ${this.sessionService.sessionInformation!.token}`
                }
            })*/
            req = req.clone({
                headers: req.headers.set('Authorization', `Bearer ${this.sessionService.sessionInformation!.token}`)
            })
        }
        return next.handle(req);
    }
    
}
