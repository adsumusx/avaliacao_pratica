import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Page } from 'src/app/model/page';
import { Router } from '@angular/router';
import { BsModalService } from 'ngx-bootstrap';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { ToastrService } from 'ngx-toastr';
import { Operador } from 'src/app/model/operador';
import { OperadorService } from 'src/app/service/operador-service';
import { Util } from 'src/app/util/util';

export interface Element {
  nomeOperador: string;
  perfilOperador: string;
  dtCadastro: string;
}
@Component({
  selector: 'app-list-operador',
  templateUrl: './list-operador.component.html',
  styleUrls: ['./list-operador.component.css']
})
export class ListOperadorComponent implements OnInit {
  public idOperador: any;
  public modalRef: BsModalRef;
  public mostrarNenhum: boolean;
  public operadoresCarregados = [];
  public loading = true;
  public totalDeOperadores: any;
  public operador = new Operador;
  public dataSource = new MatTableDataSource<Element>();
  public displayedColumns: string[] = ['nomeOperador', 'perfilOperador', 'dtCadastro', 'edit'];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  public page = new Page();
  pageTitle: string = " Lista de Operadores";
  pageHomeTitle: string = "Inicio >";
  constructor(private operadorService: OperadorService, public util: Util,
    private router: Router, private modalService: BsModalService, private toastr: ToastrService) { }

  ngOnInit() {
    parent.scroll(0, 0);
    this.loading = true;
    this.carregaOperadores();

    //else {
    //  this.router.navigate(['/login-operador'])
    //}

  }

  public carregaOperadores() {
    this.operadorService.listarOperadores(this.page).subscribe(data => {
      if (data.result != null) {
        this.totalDeOperadores = data.result.result_count;
      }
      this.page = data.page;
      if (this.totalDeOperadores > 0) {
        try {
          data.data.forEach(operador => {
            this.operador = operador;
            if (this.operador != null) {
              var objdataTable = {
                idOperador: this.operador.idOperador,
                nomeOperador: (this.operador.nomeOperador != null ? this.operador.nomeOperador : ""),
                perfilOperador: (this.operador.perfilOperador != null ? this.operador.perfilOperador : ""),
                dtCadastro: (this.operador.dtCadastro != null ? this.operador.dtCadastro : "")
              };
              let existFile = this.operadoresCarregados.filter(option => option.idOperador == objdataTable.idOperador);
              if (existFile.length == 0) {
                this.operadoresCarregados.push(objdataTable);
              }
              this.loading = false;
            } else {
              this.loading = false;
              console.log("Erro no operador")
            }
          });
          this.dataSource.data = this.operadoresCarregados;
          this.dataSource.data.length = this.totalDeOperadores;
        }
        catch (error) {
          this.loading = false;
        }
        if (this.dataSource.data.length == 0) {
          this.mostrarNenhum = true;
        }
        else {
          this.mostrarNenhum = false;
        }
      }
      this.loading = false;
    });
  }
  openDialogDelete(idOperador, template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, { class: 'modal-sm' });
    this.idOperador = idOperador
  }
  close() {
    this.modalRef.hide();
  }
  delete() {
    this.operadorService.deleteOperador(this.idOperador).subscribe(result => {
      this.showSuccess('Excluído com sucesso.');
      this.loading = true;
      setTimeout(() => {
        location.reload();
      }, 500);
      this.ngOnInit();
    }, (err) => {
      console.log(err)
    });
  }
  showSuccess(message) {
    this.toastr.success(message);
  }
  showError(message) {
    this.toastr.error(message);
  }

  public editOperador(idOperador) {
    this.router.navigate(['/edit-operador'], { queryParams: { "id": idOperador } });
  }
}
