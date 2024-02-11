import { Injectable, inject } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from "@angular/router";
import { SessionService } from "../services/session.service";

/**
 * Unauth guard.
 */
@Injectable({providedIn: 'root'})
export class UnauthGuard {

    /**
     * UnauthGuard constructor.
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
        if (this.sessionService.isLoggedIn()) {
            this.router.navigate(['posts']);
            return false;
        }
        return true;
    }
}

export const unauthGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(UnauthGuard).canActivate();
};