import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  isHiddenOperador = true;
  isHiddenPessoa = true;
  isHiddenTelefone = true;
  toggleMenu = false;

  constructor() { }

  ngOnInit() {
  }

  public manageCounter($event, number) {
    $event.stopPropagation();
    if (number === 1) {
      this.isHiddenOperador = !this.isHiddenOperador;
    }
    else if (number === 2) {
      this.isHiddenPessoa = !this.isHiddenPessoa;
    }
    else if (number === 3) {
      this.isHiddenTelefone = !this.isHiddenTelefone;
    }
  }

}
