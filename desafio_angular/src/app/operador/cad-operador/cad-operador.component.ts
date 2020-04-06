import { Util } from '../../util/util';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { Page } from '../../model/page';
import { Operador } from '../../model/operador';
import { OperadorService } from '../../service/operador-service';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-cad-operador',
  templateUrl: './cad-operador.component.html',
  styleUrls: ['./cad-operador.component.css']
})
export class CadOperadorComponent implements OnInit {

  public page = new Page();
  public modalRef: BsModalRef;
  public submitted = false;
  public time: any;
  public cad_operador: FormGroup;
  public loading = true;
  public usuarioLogado: any = "";
  public mensagem_error = false;
  public pageTitle: string = "Cadastrar Operador"
  public dataSource = new MatTableDataSource<Element>();
  public displayedColumns: string[] = ['nomeOperador', 'loginOperador', 'senhaOperador', 'perfilOperador'];
  public list = [];

  public invalid_nomeOperador = { "error": false, "tipo": "", "menssage": "" };
  public invalid_loginOperador = { "error": false, "tipo": "", "menssage": "" };
  public invalid_senhaOperador = { "error": false, "tipo": "", "menssage": "" };

  @ViewChild(MatPaginator) paginator: MatPaginator;
  public pageHomeTitle: string = "Inicio >"

  constructor(private toastr: ToastrService, private formBuilder: FormBuilder, private router: Router, private operadorService: OperadorService) {
    this.page.pageNumber = 10;
    this.page.size = 5;
  }

  ngOnInit() {
    this.cad_operador = this.formBuilder.group({
      nomeOperador: ['', [Validators.required]],
      loginOperador: ['', [Validators.required]],
      senhaOperador: ['', [Validators.required]],
      perfilOperador: ['gerente', [Validators.required]]
    });
    this.loading = false;
  }

  public onSubmit() {
    this.submitted = true;
    var alphaExp = /^[a-zA-Z---_]+$/;
    let validar = /^[a-záàâãéèêíïóôõöúçñ]+$/i;
    let valido = this.cad_operador.value.nomeOperador.split(/ +/).every(parte => validar.test(parte));
    if (valido) {
      if (this.cad_operador.value.loginOperador.match(alphaExp)) {
        if (this.cad_operador.value.senhaOperador.indexOf(" ") == -1) {
          if (this.cad_operador.valid) {
            let operador: Operador = new Operador();
            operador.nomeOperador = this.cad_operador.value.nomeOperador;
            operador.loginOperador = this.cad_operador.value.loginOperador;
            operador.senhaOperador = this.cad_operador.value.senhaOperador;
            operador.perfil = this.cad_operador.value.perfilOperador;
            var data = new Date(),
              dia = data.getDate().toString().padStart(2, '0'),
              mes = (data.getMonth() + 1).toString().padStart(2, '0'), //+1 pois no getMonth Janeiro começa com zero.
              ano = data.getFullYear();
            operador.dtCadastro = dia + "/" + mes + "/" + ano;
            this.operadorService.addOperador(operador).subscribe(data => {
              if (data.type == "success") {
                this.showSuccess('Operador cadastrado com sucesso!');
              }
              else if (data.type == "error") {
                this.showError('Operador já cadastrado!');
              }
              else {
                this.showError('Você não tem permissão para cadastrar um operador!');
              }
            });
          }
          else {
            this.mensagem_error = true;
            window.scroll(0, 0);
          }
        }
        else {
          this.cad_operador.controls['senhaOperador'].setErrors({ "invalid_senhaOperador": 'senhaOperador' });
          this.invalid_senhaOperador.error = true;
          this.invalid_senhaOperador.menssage = "A senha do operador não pode conter espaços";
          return null;
        }
      }
      else {
        this.cad_operador.controls['loginOperador'].setErrors({ "invalid_loginOperador": 'loginOperador' });
        this.invalid_loginOperador.error = true;
        this.invalid_loginOperador.menssage = "O login do operador só pode conter letras e '-', '_'";
        return null;
      }
    }
    else {
      this.cad_operador.controls['nomeOperador'].setErrors({ "invalid_nomeOperador": 'nomeOperador' });
      this.invalid_nomeOperador.error = true;
      this.invalid_nomeOperador.menssage = "O nome do operador só pode conter letras e espaços";
      return null;
    }
  }

  showSuccess(message) {
    this.toastr.success(message);
  }

  showError(message) {
    this.toastr.error(message);
  }

  public voltar() {
    window.history.go(-1);
  }

  get f() { return this.cad_operador.controls; }

}
