import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterRequest } from '../../models/registerRequest.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  public onError: boolean = false;

  public form = this.fb.group({
    username: [
      '', 
      [
        Validators.required,
        Validators.maxLength(50)
      ],
    ],
    email: [
      '',
      [
        Validators.required,
        Validators.maxLength(255),
        Validators.email
      ]
    ],
    password: [
      '',
      [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(255),
        Validators.pattern('(?=.*[0-9])(?=.*[!:;,+?$-])(?=.*[a-z])(?=.*[A-Z]).*')
      ]
    ]
  });

  constructor (private authService: AuthService,
               private fb: FormBuilder,
               private router: Router) {}

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe({
      next: _ => this.router.navigate(['auth/login']),
      error: _ => this.onError = true
    })
  }
}
