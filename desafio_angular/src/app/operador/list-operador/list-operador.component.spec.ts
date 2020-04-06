import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOperadorComponent } from './list-operador.component';

describe('ListOperadorComponent', () => {
  let component: ListOperadorComponent;
  let fixture: ComponentFixture<ListOperadorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListOperadorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOperadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
