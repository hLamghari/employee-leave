import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EmployeService } from '../../../core/services/employe.service';
import { Employe } from '../../../core/models/employe.model';

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
    this.employeService.getEmployeById(id).subscribe(emp => this.employe = emp);
  }
}