import { TestBed } from '@angular/core/testing';

import { ServiceactorService } from './serviceactor.service';

describe('ServiceactorService', () => {
  let service: ServiceactorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceactorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
