import { Component, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {

  showFiller = false;
  title = 'sicoe';
  isCollapsed = true;
  @Input() candidatoSSO: any;
  unidade: string = "PGFN"
  hora: number;

  constructor(
    public dialog: MatDialog,
    private toastr: ToastrService,

    private route: ActivatedRoute, private router: Router) {

    // setInterval(() => {
    this.hora = Date.now();
    // }, 1);
  }

  getUser(): any {
    return this.candidatoSSO;
  }

  comeBack() {
    if (this.candidatoSSO) {
      window.open(this.candidatoSSO.voltarPortal, "_Self");
    }
    else if (this.candidatoSSO.voltarPortal) {
      window.open(this.route.snapshot.data['urlPortal'], "_Self");
    }
  }

  // public openDialogLogout() {
  //   this.ModallogoutService.logout();
  //   this.dialog.open(ModalLogoutComponent);
  // }

  public logout() {

  }

}
