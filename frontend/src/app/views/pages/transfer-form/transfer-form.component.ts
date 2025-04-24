import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Account } from 'src/app/core/models/account';
import { Periodicity, TransferRequest } from 'src/app/core/models/transfer-request';
import { AccountService } from 'src/app/services/account.service';
import { TransferService } from 'src/app/services/transfer.service';

@Component({
  selector: 'app-transfer-form',
  standalone: true,
  imports: [RouterModule, CommonModule, ReactiveFormsModule],
  templateUrl: './transfer-form.component.html',
  styleUrl: './transfer-form.component.scss'
})
export class TransferFormComponent {
  transferForm!: FormGroup;
  accounts: Account[] = [];

  periodicities: Periodicity[] = [null, 'MONTHLY', 'YEARLY'];
  isSubmitting = false;
  backendError: string | null = null;
  formError: string | null = null;
  successMessage: string | null = null;

  currentAccountId = 1; // This should be dynamically set based on the logged-in user

  constructor(
    private fb: FormBuilder,
    private accountService: AccountService,
    private transferService: TransferService
  ) {}

  ngOnInit(): void {
    this.transferForm = this.fb.group({
      sourceAccountId: [null, Validators.required],
      destinationAccountId: [null, Validators.required],
      amount: [null, [Validators.required, Validators.min(0.01)]],
      executionDate: ['', Validators.required],
      periodicite: [null]
    });

    this.accountService.getAllAccounts(this.currentAccountId).subscribe({
      next: (accounts : any) => this.accounts = accounts,
      error: () => { this.formError = 'Could not load accounts.'; }
    });
  }

  onSubmit(): void {
    this.resetMessages();
  
    if (!this.validateForm()) {
      return;
    }
  
    const formValue = this.transferForm.value as TransferRequest;
  
    if (!this.validateAccountsDifferent(formValue)) {
      return;
    }
  
    this.processTransfer(formValue);
  }
  
  private resetMessages(): void {
    this.formError = null;
    this.backendError = null;
    this.successMessage = null;
  }
  
  private validateForm(): boolean {
    if (this.transferForm.invalid) {
      this.formError = 'Please fill all required fields with valid values.';
      this.transferForm.markAllAsTouched();
      return false;
    }
    return true;
  }
  
  private validateAccountsDifferent(formValue: TransferRequest): boolean {
    if (formValue.sourceAccountId === formValue.destinationAccountId) {
      this.formError = 'Source and destination accounts must be different.';
      return false;
    }
    return true;
  }
  
  private processTransfer(formValue: TransferRequest): void {
    this.isSubmitting = true;
    this.transferService.makeTransfer(formValue).subscribe({
      next: () => {
        this.successMessage = 'Transfer successful!';
        this.isSubmitting = false;
        this.transferForm.reset();
      },
      error: (err: any) => {
        this.isSubmitting = false;
        const errorObj = err?.error;
        if (errorObj && errorObj.errorCode && errorObj.message) {
          this.backendError = `[${errorObj.errorCode}] ${errorObj.message}`;
        } else {
          this.backendError = 'An error occurred during transfer.';
        }
      }
    });
  }
}