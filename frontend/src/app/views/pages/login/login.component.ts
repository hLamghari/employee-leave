import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  returnUrl: any;

  loginForm!: FormGroup;
  loginBtnLoading!: boolean;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private authService: AuthService) { }

  ngOnInit(): void {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  onLoggedin() {
    if (this.loginForm.valid) {
      this.loginBtnLoading = true;
      let user : any
      user.email = this.loginForm.value.email;
      user.password = this.loginForm.value.password;
      this.authService.login(user).subscribe({
        next: (response: any) => {
          this.loginBtnLoading = false;
          this.loginForm.reset();
          this.authService.setAuthToken(response.accessToken)
          this.router.navigate([this.returnUrl]);

        },
        error: (e : any) => {
          this.loginBtnLoading = false;
          
          if(e.status === 401 || e.status === 403){
            alert(`Adresse email ou mot de passe incorrect`);
          }else{
            alert(`Problème de connexion, merci de réessayer`);
          }
          
        }
      });
    } else {
      this.loginForm.markAllAsTouched();
    }
  }

}
