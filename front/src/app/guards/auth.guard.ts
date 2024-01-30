import { Injectable, inject } from "@angular/core";
import { SessionService } from "../services/session.service";
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from "@angular/router";

@Injectable({providedIn: 'root'})
export class AuthGuard {

    constructor (private router: Router,
                 private sessionService: SessionService) {}

    canActivate(): boolean {
        if (!this.sessionService.isLogged) {
            this.router.navigate(['login']);
            return false;
        }
        return true;
    }
}

export const authGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(AuthGuard).canActivate();
};