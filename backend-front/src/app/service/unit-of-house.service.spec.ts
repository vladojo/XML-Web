import { TestBed } from '@angular/core/testing';

import { UnitOfHouseService } from './unit-of-house.service';

describe('UnitOfHouseService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UnitOfHouseService = TestBed.get(UnitOfHouseService);
    expect(service).toBeTruthy();
  });
});
