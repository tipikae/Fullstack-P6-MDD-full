import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { SessionService } from "../services/session.service";

/**
 * JWT interceptor.
 */
@Injectable({ providedIn: 'root' })
export class JwtInterceptor implements HttpInterceptor {

    /**
     * JwtInterceptor constructor.
     * @param sessionService Session service.
     */
    constructor (private sessionService: SessionService) { }
    
    /**
     * Intercepts request to inject JWT.
     * @param req Request.
     * @param next Handler.
     * @returns Observable.
     */
    public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (this.sessionService.isLoggedIn()) {
            req = req.clone({
                setHeaders: {
                    Authorization: `Bearer ${this.sessionService.getSessionInformation()?.token}`
                }
            })
        }
        return next.handle(req);
    }
    
}
