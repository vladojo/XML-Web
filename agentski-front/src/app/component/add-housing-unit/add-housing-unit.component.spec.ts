import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddHousingUnitComponent } from './add-housing-unit.component';

describe('AddHousingUnitComponent', () => {
  let component: AddHousingUnitComponent;
  let fixture: ComponentFixture<AddHousingUnitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddHousingUnitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddHousingUnitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
