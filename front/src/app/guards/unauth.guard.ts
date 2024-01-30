import { Injectable, inject } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from "@angular/router";
import { SessionService } from "../services/session.service";

@Injectable({providedIn: 'root'})
export class UnauthGuard {

    constructor (private router: Router,
                 private sessionService: SessionService) {}

    canActivate(): boolean {
        if (this.sessionService.isLogged) {
            this.router.navigate(['posts']);
            return false;
        }
        return true;
    }
}

export const unauthGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(UnauthGuard).canActivate();
};