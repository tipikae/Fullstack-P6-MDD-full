import { Injectable, inject } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from "@angular/router";
import { SessionService } from "../services/session.service";

/**
 * Auth guard.
 */
@Injectable({providedIn: 'root'})
export class AuthGuard {

    /**
     * AuthGuard constructor.
     * @param router Router.
     * @param sessionService Session service. 
     */
    constructor (private router: Router,
                 private sessionService: SessionService) {}

    /**
     * Check if route can be activate.
     * @returns boolean.
     */
    canActivate(): boolean {
        if (!this.sessionService.isLoggedIn()) {
            this.router.navigate(['']);
            return false;
        }
        return true;
    }
}

export const authGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(AuthGuard).canActivate();
};