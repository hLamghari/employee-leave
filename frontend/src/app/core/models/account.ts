import { AccountType } from "../enums/account-type";


export interface Account {
  id: number;
  nom: string;
  iban: string;
  type: AccountType;
  amount: number;         // Use number for BigDecimal in Angular
  tauxInteret: number;    // Use number for BigDecimal in Angular
}