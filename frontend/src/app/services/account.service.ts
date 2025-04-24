import { Injectable } from '@angular/core';
import { getMockedAccountById, MOCKED_ALL_ACCOUNTS, MOCKED_GROUPED_ACCOUNTS } from '../core/mocks/mocked-accounts';
import { of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private accountEndpointUrl = environment.API_BASE_URL + '/v1/accounts';

  constructor(private http: HttpClient) {}


  getAccountById(id: number) {
    //return this.http.get(`${this.accountEndpointUrl}/${id}`);
    return of(getMockedAccountById(id));
  }

  getGroupedAccounts(userId: any) {
    //return this.http.get(`${this.accountEndpointUrl}/users/${userId}/grouped`);
    // we will mock the return
    return of(MOCKED_GROUPED_ACCOUNTS);
  }

  //service used to list all account for the transfer
  getAllAccounts(userId: any){
    //return this.http.get(`${this.accountEndpointUrl}/users/${userId}`);
    return of(MOCKED_ALL_ACCOUNTS)
  }
}
