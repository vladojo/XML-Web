import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazRezervacijaComponent } from './prikaz-rezervacija.component';

describe('PrikazRezervacijaComponent', () => {
  let component: PrikazRezervacijaComponent;
  let fixture: ComponentFixture<PrikazRezervacijaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazRezervacijaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazRezervacijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
