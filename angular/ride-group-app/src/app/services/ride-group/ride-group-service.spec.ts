import { TestBed } from '@angular/core/testing';

import { RideGroupService } from './ride-group-service';

describe('RideGroupService', () => {
  let service: RideGroupService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RideGroupService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
