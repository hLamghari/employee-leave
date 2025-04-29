import { Component } from '@angular/core';
import { CongeService } from '../../../core/services/conge.service';
import { CongeRequest } from '../../../core/models/conge-request.model';
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
      employeId: [null, Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      typeConge: ['', Validators.required],
    });
  }

  submit(): void {
    if (this.form.valid) {
      this.congeService.demanderConge(this.form.value).subscribe({
        next: () => this.message = 'Demande envoyée avec succès !',
        error: err => this.message = err.error.message || 'Erreur inconnue'
      });
    }
  }
}