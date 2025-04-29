export interface CongeRequest {
    employeId: number;
    dateDebut: string;
    dateFin: string;
    typeConge: 'CONGE_PAYE' | 'RTT' | 'SANS_SOLDE';
  }