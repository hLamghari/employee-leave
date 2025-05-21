import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employe } from '../models/employe.model';
import { Leave } from '@models/leave.model';
import { EmployeLeaves } from '@models/employeeLeaves.model';

@Injectable({ providedIn: 'root' })
export class EmployeService {
  private apiUrl = '/api/employees';

  constructor(private http: HttpClient) {}

  getAllEmployes(): Observable<Employe[]> {
    return this.http.get<Employe[]>(this.apiUrl);
  }

  getEmployeById(id: number): Observable<Employe> {
    return this.http.get<Employe>(`${this.apiUrl}/${id}`);
  }
  
  getEmployeeConges(employeeId: number): Observable<EmployeLeaves>{
    return this.http.get<EmployeLeaves>(this.apiUrl + '/' + employeeId + "/leaves");
  }
}