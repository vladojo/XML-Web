import { TestBed } from '@angular/core/testing';

import { HousingUnitsService } from './housing-units.service';

describe('HousingUnitsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HousingUnitsService = TestBed.get(HousingUnitsService);
    expect(service).toBeTruthy();
  });
});
