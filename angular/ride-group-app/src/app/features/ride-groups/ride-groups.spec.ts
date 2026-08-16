import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RideGroups } from './ride-groups';

describe('RideGroups', () => {
  let component: RideGroups;
  let fixture: ComponentFixture<RideGroups>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RideGroups],
    }).compileComponents();

    fixture = TestBed.createComponent(RideGroups);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
