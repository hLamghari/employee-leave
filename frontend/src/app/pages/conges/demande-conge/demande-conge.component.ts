import { Component } from '@angular/core';
import { CongeService } from '@services/conge.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-demande-conge',
  templateUrl: './demande-conge.component.html',
  styleUrl: './demande-conge.component.css'
})
export class DemandeCongeComponent {
  form: FormGroup;
  message = '';
  error = false;

  constructor(private fb: FormBuilder, private congeService: CongeService) {
    this.form = this.fb.group({
      employeeId: [null, Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      leaveType: ['', Validators.required],
    });
  }

  submit(): void {
    console.log('click');
    
    if (this.form.valid) {
      this.congeService.demanderConge(this.form.value).subscribe({
        next: () => {
          this.message = 'Demande envoyée avec succès !';
          this.error = false;
          this.init();
        },
        error: err => {
          this.message =  "Erreur : " + err.error.message || 'Erreur inconnue';
          this.error = true;
        }
      });
    }
  }

  init(): void {
    this.form.reset()
  }
}