import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AccountService } from 'src/app/services/account.service';
import { LoadingErrorComponent } from "../../components/loading-error/loading-error.component";
import { AuthErrorComponent } from "../../components/auth-error/auth-error.component";
import { LoadingComponent } from "../../components/loading/loading.component";
import { CommonModule } from '@angular/common';
import { GroupedAccounts } from 'src/app/core/models/grouped-account';

@Component({
  selector: 'app-account-list',
  standalone: true,
  imports: [RouterModule, LoadingErrorComponent, AuthErrorComponent, LoadingComponent, CommonModule],
  templateUrl: './account-list.component.html',
  styleUrl: './account-list.component.scss'
})
export class AccountListComponent {

  groupedAccounts!: GroupedAccounts;
  isLoading = false;
  loadingError = false;
  authError = false;

  currentUserId = 1; // this should be replaced with the actual user id from the authentication service

  constructor(
    private accountService: AccountService
  ) { }

  ngOnInit(): void {
    this.getGroupedAccounts();
  }

  getGroupedAccounts(){
    this.isLoading = true;
    this.accountService.getGroupedAccounts(this.currentUserId).subscribe({
      next: (response: any) => {
        //timeout to simulate the loading.. and be able to see the loader
        setTimeout(() => {
          this.groupedAccounts = response;
          this.isLoading = false;
        }, 800);
      },
      error: (e) => {
        if (e.status === 401 || e.status === 403) {
          this.isLoading = false;
          this.authError = true;
        } else {
          this.isLoading = false;
          this.loadingError = true;
        }
      }
    })
  }
}
