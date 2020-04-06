import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ToastrModule } from 'ngx-toastr';
import { AppRoutingModule } from './app-routing.module';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { MatDialogModule, MatInputModule, MatAutocompleteModule, MatFormFieldModule, MatCard, MatButtonModule, MatCheckboxModule } from '@angular/material';
import { BsDropdownModule, CollapseModule, BsDatepickerModule } from 'ngx-bootstrap';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { NgxLoadingModule } from 'ngx-loading';
import { ModalModule } from 'ngx-bootstrap';
import { CurrencyMaskModule } from "ng2-currency-mask";
import { NgxMaskModule } from 'ngx-mask';
import { MatTabsModule } from '@angular/material/tabs';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { CadOperadorComponent } from './operador/cad-operador/cad-operador.component';
import { EditOperadorComponent } from './operador/edit-operador/edit-operador.component';
import { ListOperadorComponent } from './operador/list-operador/list-operador.component';
import { LoginOperadorComponent } from './operador/login-operador/login-operador.component';

@NgModule({
  declarations: [
    AppComponent,
    CadOperadorComponent,
    EditOperadorComponent,
    ListOperadorComponent,
    LoginOperadorComponent,
    MenuComponent
  ],
  exports: [BsDropdownModule, BsDatepickerModule],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
  imports: [
    BrowserModule,
    MatMenuModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ModalModule.forRoot(),
    NgxLoadingModule.forRoot({}),
    NgxMaskModule.forRoot(),
    MatTabsModule,
    MatSidenavModule,
    MatDialogModule,
    RouterModule,
    CurrencyMaskModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatPaginatorModule,
    MatTableModule,
    HttpClientModule,
    CollapseModule.forRoot(),
    ToastrModule.forRoot(),
    BsDatepickerModule.forRoot(),
    BsDropdownModule.forRoot(),
    MatButtonModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
