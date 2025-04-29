import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employe } from '../models/employe.model';

@Injectable({ providedIn: 'root' })
export class EmployeService {
  private apiUrl = '/api/employes';

  constructor(private http: HttpClient) {}

  getAllEmployes(): Observable<Employe[]> {
    return this.http.get<Employe[]>(this.apiUrl);
  }

  getEmployeById(id: number): Observable<Employe> {
    return this.http.get<Employe>(`${this.apiUrl}/${id}`);
  }
}