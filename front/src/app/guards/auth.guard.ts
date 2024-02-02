import { Injectable, inject } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from "@angular/router";
import { SessionService } from "../services/session.service";

@Injectable({providedIn: 'root'})
export class AuthGuard {

    constructor (private router: Router,
                 private sessionService: SessionService) {}

    canActivate(): boolean {
        if (!this.sessionService.isLoggedIn()) {
            this.router.navigate(['home']);
            return false;
        }
        return true;
    }
}

export const authGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(AuthGuard).canActivate();
};