import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EmployeService } from '../../../services/employe.service';
import { Employe } from '../../../models/employe.model';

@Component({
  selector: 'app-employe-detail',
  templateUrl: './employe-detail.component.html'
})
export class EmployeDetailComponent implements OnInit {
  employe?: Employe;

  constructor(
    private route: ActivatedRoute,
    private employeService: EmployeService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.employeService.getEmployeById(id).subscribe({
      next: emp => this.employe = emp,
      error: err => {
        console.error('Erreur lors de la récupération de l\'employé :', err);
      }
    });
  }
}