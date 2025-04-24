import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { TransferRequest } from '../core/models/transfer-request';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

  private transferEndpointUrl = environment.API_BASE_URL + '/v1/transfers';
  
  constructor(private http: HttpClient) {}

  makeTransfer(request: TransferRequest): Observable<any> {
    return this.http.post(`${this.transferEndpointUrl}`, request);
  }
}
