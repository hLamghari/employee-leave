import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CongeRequest } from '../models/conge-request.model';
import { Leave } from '@models/leave.model';

@Injectable({ providedIn: 'root' })
export class CongeService {
  private apiUrl = '/api/leaves';

  constructor(private http: HttpClient) {}

  demanderConge(conge: CongeRequest): Observable<any> {
    return this.http.post(this.apiUrl, conge);
  }
}
