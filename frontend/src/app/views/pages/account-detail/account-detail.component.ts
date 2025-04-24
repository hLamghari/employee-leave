import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { AccountService } from 'src/app/services/account.service';
import { LoadingComponent } from '../../components/loading/loading.component';
import { AuthErrorComponent } from '../../components/auth-error/auth-error.component';
import { LoadingErrorComponent } from '../../components/loading-error/loading-error.component';
import { Account } from 'src/app/core/models/account';

@Component({
  selector: 'app-account-detail',
  standalone: true,
  imports: [RouterModule, LoadingErrorComponent, AuthErrorComponent, LoadingComponent, CommonModule],
  templateUrl: './account-detail.component.html',
  styleUrl: './account-detail.component.scss'
})
export class AccountDetailComponent {

  account!: Account;
  isLoading = false;
  loadingError = false;
  authError = false;
  errorMessage!: string;

  constructor(
    private route: ActivatedRoute,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.getAccount();
  }

  private getAccount(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    const id = idParam ? +idParam : NaN;

    if (isNaN(id)) {
      this.errorMessage = 'Account not found: Invalid ID';
      return;
    }

    this.isLoading = true;
    this.accountService.getAccountById(id).subscribe({
      next: (acc: any) => {
        this.account = acc;
        this.isLoading = false;
      },
      error: (e) => {
        this.isLoading = false;
        if (e.status === 401 || e.status === 403) {
          this.authError = true;
        } else if (e.status === 404) {
          this.errorMessage = 'Account not found.';
        } else {
          this.loadingError = true;
        }
      }
    });
  }
}