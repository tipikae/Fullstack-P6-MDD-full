import { Component, OnDestroy } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from '../../models/loginRequest.model';
import { SessionInformation } from 'src/app/models/sessionInformation.model';
import { SessionService } from 'src/app/services/session.service';
import { Subscription } from 'rxjs';

/**
 * Login component.
 */
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnDestroy {

  private loginSubscription!: Subscription;

  public hide: boolean = true;
  public onError: boolean = false;

  public form = this.fb.group({
    email: [
      '',
      [
        Validators.required
      ]
    ],
    password: [
      '',
      [
        Validators.required
      ]
    ]
  });

  /**
   * LoginComponent constructor.
   * @param authService  Auth service.
   * @param fb Form builder.
   * @param router Router.
   * @param sessionService Session service.
   */
  constructor (private authService: AuthService,
               private fb: FormBuilder,
               private router: Router,
               private sessionService: SessionService) {}

  /**
   * Call on destroy.
   */
  ngOnDestroy(): void {
    if(this.loginSubscription != undefined) this.loginSubscription.unsubscribe();
  }

  /**
   * Submit login form.
   */
  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.loginSubscription = this.authService.login(loginRequest).subscribe({
      next: (response: SessionInformation) => {
        this.sessionService.login(response);
        this.router.navigate(['posts']);
      },
      error: error => this.onError = true
    })
  }

}
