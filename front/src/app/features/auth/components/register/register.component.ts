import { Component, OnDestroy } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterRequest } from '../../models/registerRequest.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SharedService } from 'src/app/shared/shared.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnDestroy {

  public registerSubscription!: Subscription;

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
               private router: Router,
               private matSnackBar: MatSnackBar,
               private sharedService: SharedService) {}             
               
  ngOnDestroy(): void {
    if (this.registerSubscription != undefined) this.registerSubscription.unsubscribe();;
  }

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.registerSubscription = this.authService.register(registerRequest).subscribe({
      next: _ => {
        this.matSnackBar.open('Registration success !', 'Close', { duration: 3000 })
        this.router.navigate(['auth/login']);
      },
      error: _ => this.onError = true
    })
  }

  public getErrorText(field: FormControl): string {
    return this.sharedService.getFormControlErrorText(field);
  }
}
