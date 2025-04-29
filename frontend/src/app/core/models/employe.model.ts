export interface Employe {
    id: number;
    nom: string;
    prenom: string;
    categorie: 'CADRE' | 'NON_CADRE';
    dateEmbauche: string;
    soldeConges: number;
    soldeRTT?: number;
  }