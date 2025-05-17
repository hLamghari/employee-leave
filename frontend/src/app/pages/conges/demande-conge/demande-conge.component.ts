import { Component } from '@angular/core';
import { CongeService } from '@services/conge.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-demande-conge',
  templateUrl: './demande-conge.component.html'
})
export class DemandeCongeComponent {
  form: FormGroup;
  message = '';

  constructor(private fb: FormBuilder, private congeService: CongeService) {
    this.form = this.fb.group({
      employeeId: [null, Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      leaveType: ['', Validators.required],
    });
  }

  submit(): void {
    if (this.form.valid) {
      this.congeService.demanderConge(this.form.value).subscribe({
        next: () => {
          this.message = 'Demande envoyée avec succès !';
          this.init();
        },
        error: err => this.message =  "Erreur : " + err.error.message || 'Erreur inconnue'
      });
    }
  }

  init(): void {
    this.form.reset()
  }
}