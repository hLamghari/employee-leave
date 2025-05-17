import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DemandeCongeComponent } from '@components/conges/demande-conge/demande-conge.component';
import { AppComponent } from './app.component';
import { EmployeListComponent } from '@components/employes/employe-list/employe-list.component';
import { EmployeDetailComponent } from '@components/employes/employe-detail/employe-detail.component';
import { CongeListComponent } from '@components/conges/conge-list/conge-list.component';
import { LayoutComponent } from './layout/layout.component';
import { HeaderComponent } from "./layout/header/header.component";
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { BreadcrumbComponent } from './layout/breadcrumb/breadcrumb.component';


@NgModule({
  declarations: [
    AppComponent,
    DemandeCongeComponent,
    EmployeListComponent,
    EmployeDetailComponent,
    CongeListComponent,
    LayoutComponent,
    HeaderComponent,
    SidebarComponent,
    BreadcrumbComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    
],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
