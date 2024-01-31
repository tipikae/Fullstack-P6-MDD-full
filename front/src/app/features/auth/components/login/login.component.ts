import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from '../../models/loginRequest.model';
import { SessionInformation } from 'src/app/models/sessionInformation.model';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  public hide: boolean = true;
  public onError: boolean = false;

  public form = this.fb.group({
    email: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ],
    password: [
      '',
      [
        Validators.required,
        Validators.minLength(3)
      ]
    ]
  });

  constructor (private authService: AuthService,
               private fb: FormBuilder,
               private router: Router,
               private sessionService: SessionService) {}

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe({
      next: (response: SessionInformation) => {
        this.sessionService.login(response);
        this.router.navigate(['posts']);
      },
      error: error => this.onError = true
    })
  }

}
