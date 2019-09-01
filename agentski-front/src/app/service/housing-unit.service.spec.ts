import { TestBed } from '@angular/core/testing';

import { HousingUnitService } from './housing-unit.service';

describe('HousingUnitService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HousingUnitService = TestBed.get(HousingUnitService);
    expect(service).toBeTruthy();
  });
});
