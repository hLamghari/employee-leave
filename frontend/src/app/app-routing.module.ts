import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeListComponent } from '@components/employes/employe-list/employe-list.component';
import { EmployeDetailComponent } from '@components/employes/employe-detail/employe-detail.component';
import { DemandeCongeComponent } from '@components/conges/demande-conge/demande-conge.component';


const routes: Routes = [
  { path: '', redirectTo: 'employees', pathMatch: 'full' },
  { path: 'employees', component: EmployeListComponent },
  { path: 'employees/:id', component: EmployeDetailComponent },
  { path: 'leave-demand', component: DemandeCongeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

