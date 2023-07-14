export class Customer {
    customerId! : number;
    storeId!: {
      storeId: number;
      managerStaffId: number;
      addressId: number;
      lastUpdate: string;
    };
    firstName!: string;
    lastName!: string;
    email!: string;
    addressId!: {
      addressId: number;
      cityId: {
          cityId: number;
          countryId: {
          countryId: number;
          country: string;
          lastUpdate: string;
        };
        city: string;
        lastUpdate: string;
      };
      address: string;
      address2: string | null;
      district: string;
      postal_code: string;
      phone: string;
      location: any;
      lastUpdate: string;
    };
    active!: string;
    createDate!: string;
    lastUpdate!: string;
  }
  