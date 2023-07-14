import { TestBed } from '@angular/core/testing';

import { servicestore } from './servicestore.service';

describe('ServicestoreService', () => {
  let service: servicestore;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(servicestore);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
