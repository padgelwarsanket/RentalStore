import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RentreceiptComponent } from './rentreceipt.component';

describe('RentreceiptComponent', () => {
  let component: RentreceiptComponent;
  let fixture: ComponentFixture<RentreceiptComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RentreceiptComponent]
    });
    fixture = TestBed.createComponent(RentreceiptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
