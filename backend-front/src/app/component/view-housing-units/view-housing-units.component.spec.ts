import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewHousingUnitsComponent } from './view-housing-units.component';

describe('ViewHousingUnitsComponent', () => {
  let component: ViewHousingUnitsComponent;
  let fixture: ComponentFixture<ViewHousingUnitsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewHousingUnitsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewHousingUnitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
