import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginOperadorComponent } from './login-operador.component';

describe('LoginOperadorComponent', () => {
  let component: LoginOperadorComponent;
  let fixture: ComponentFixture<LoginOperadorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginOperadorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginOperadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
