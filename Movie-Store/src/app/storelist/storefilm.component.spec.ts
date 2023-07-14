import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StoreListComponent } from './storefilm.component';

describe('StorefilmComponent', () => {
  let component: StoreListComponent;
  let fixture: ComponentFixture<StoreListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StoreListComponent]
    });
    fixture = TestBed.createComponent(StoreListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
