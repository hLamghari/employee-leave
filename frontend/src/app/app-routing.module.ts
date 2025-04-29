import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeListComponent } from './pages/employes/employe-list/employe-list.component';
import { EmployeDetailComponent } from './pages/employes/employe-detail/employe-detail.component';
import { DemandeCongeComponent } from './pages/conges/demande-conge/demande-conge.component';


const routes: Routes = [
  { path: '', redirectTo: 'employes', pathMatch: 'full' },
  { path: 'employes', component: EmployeListComponent },
  { path: 'employes/:id', component: EmployeDetailComponent },
  { path: 'demande-conge', component: DemandeCongeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

<nav>
  <a routerLink="/employes">Liste des employés</a>
  <a routerLink="/demande-conge">Demande de congé</a>
</nav>
<router-outlet></router-outlet>
