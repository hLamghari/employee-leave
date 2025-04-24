import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContentComponent } from './views/layout/content/content.component';
import { AccountListComponent } from './views/pages/account-list/account-list.component';
import { AccountDetailComponent } from './views/pages/account-detail/account-detail.component';
import { TransferFormComponent } from './views/pages/transfer-form/transfer-form.component';
import { LoginComponent } from './views/pages/login/login.component';
import { NotFoundComponent } from './views/pages/not-found/not-found.component';


const routes: Routes = [
  {
    path: '',
    component: ContentComponent,
    children: [
      { path: 'accounts', component: AccountListComponent },
      { path: 'accounts/:id', component: AccountDetailComponent },
      { path: 'transfer', component: TransferFormComponent },
      { path: 'login', component: LoginComponent },
      { path: '404', component: NotFoundComponent },
      { path: '', redirectTo: 'accounts', pathMatch: 'full' }
    ]
  },

  { path: '**', redirectTo: '404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
