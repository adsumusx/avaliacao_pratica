import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CadOperadorComponent } from './operador/cad-operador/cad-operador.component';
import { LoginOperadorComponent } from './operador/login-operador/login-operador.component';
import { EditOperadorComponent } from './operador/edit-operador/edit-operador.component';
import { ListOperadorComponent } from './operador/list-operador/list-operador.component';


let urlTicket = window.location.search;
let ticket: string = "";
if (urlTicket != null) {
  ticket = urlTicket.split('=')[1];
}

const routes: Routes = [
  //Adicionar as Rotas aqui

  { path: '', redirectTo: '/lista-operador', pathMatch: 'full' },
  {
    path: 'lista-operador',
    component: ListOperadorComponent

  },
  {
    path: 'cad-operador',
    component: CadOperadorComponent
  },
  {
    path: 'edit-operador',
    component: EditOperadorComponent
  },
  {
    path: 'valida-login',
    component: LoginOperadorComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }