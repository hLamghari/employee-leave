import { Account } from "./account";


export interface GroupedAccounts {
  currentAccounts: Account[];
  savingsAccounts: Account[];
}
