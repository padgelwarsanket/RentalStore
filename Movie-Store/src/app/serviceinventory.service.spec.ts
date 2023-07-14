import { TestBed } from '@angular/core/testing';

import { Serviceinventory } from './serviceinventory.service';

describe('ServiceinventoryService', () => {
  let service: Serviceinventory;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Serviceinventory);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
