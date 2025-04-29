import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CongeRequest } from '../models/conge-request.model';

@Injectable({ providedIn: 'root' })
export class CongeService {
  private apiUrl = '/api/conges';

  constructor(private http: HttpClient) {}

  demanderConge(conge: CongeRequest): Observable<any> {
    return this.http.post(this.apiUrl, conge);
  }
}
