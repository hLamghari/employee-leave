import { Component, OnInit } from '@angular/core';
import { EmployeService } from '../../../core/services/employe.service';
import { Employe } from '../../../core/models/employe.model';

@Component({
  selector: 'app-employe-list',
  templateUrl: './employe-list.component.html'
})
export class EmployeListComponent implements OnInit {
  cadres: Employe[] = [];
  nonCadres: Employe[] = [];

  constructor(private employeService: EmployeService) {}

  ngOnInit(): void {
    this.employeService.getAllEmployes().subscribe(data => {
      this.cadres = data.filter(e => e.categorie === 'CADRE');
      this.nonCadres = data.filter(e => e.categorie === 'NON_CADRE');
    });
  }
}
