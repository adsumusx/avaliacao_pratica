import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditOperadorComponent } from './edit-operador.component';

describe('EditOperadorComponent', () => {
  let component: EditOperadorComponent;
  let fixture: ComponentFixture<EditOperadorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditOperadorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditOperadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
