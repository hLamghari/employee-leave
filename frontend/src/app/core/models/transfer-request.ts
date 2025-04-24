export type Periodicity = 'MONTHLY' | 'YEARLY' | null;

export interface TransferRequest {
  sourceAccountId: number;
  destinationAccountId: number;
  montant: number;            
  executionDate: string;    
  periodicite: Periodicity; 
}