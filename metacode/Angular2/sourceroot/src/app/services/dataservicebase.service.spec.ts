import { TestBed, inject } from '@angular/core/testing';

import { DataservicebaseService } from './dataservicebase.service';

describe('DataservicebaseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DataservicebaseService]
    });
  });

  it('should be created', inject([DataservicebaseService], (service: DataservicebaseService) => {
    expect(service).toBeTruthy();
  }));
});
