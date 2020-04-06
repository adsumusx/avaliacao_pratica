import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadOperadorComponent } from './cad-operador.component';

describe('CadOperadorComponent', () => {
  let component: CadOperadorComponent;
  let fixture: ComponentFixture<CadOperadorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadOperadorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadOperadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
