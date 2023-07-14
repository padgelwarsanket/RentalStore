//package com.sprint.filmerentalstore.controller.positive;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.sprint.filmerentalstore.dto.AddressDto;
//import com.sprint.filmerentalstore.dto.CityDto;
//import com.sprint.filmerentalstore.dto.CountryDto;
//import com.sprint.filmerentalstore.dto.CustomerDto;
//import com.sprint.filmerentalstore.service.CustomerService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//class CustomerControllerTest {
//	
//	@MockBean
//    private CustomerService customerService;
//
//    @Autowired
//    private MockMvc mockmvc;
//
//    
////    @Test
////    public void testCreateCustomer() throws Exception {
////        
////        CustomerDto customerDto = new CustomerDto();
////        when(customerService.registerCustomer(any(CustomerDto.class))).thenReturn(customerDto);
////        ResponseEntity<CustomerDto> response = customerController.createCustomer(customerDto);
////        assertNotNull(response);
////        assertEquals(HttpStatus.OK, response.getStatusCode());
////        assertEquals(customerDto, response.getBody());
////
////        
////        verify(customerService, times(1)).registerCustomer(customerDto);
////    }
//    
//    
//    
//    @Test
//    public void testGetCustomerByLastName() throws Exception{
//
//        String lastName="amboji";
//        CustomerDto customerDtos=new CustomerDto();
//        customerDtos.setLastName(lastName);
//        List<CustomerDto> customerList=Arrays.asList(customerDtos);
//        when(customerService.getCustomerByLastName(lastName)).thenReturn(customerList);
//        mockmvc.perform(get("/customer/lastname/{ln}",lastName))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$[0].lastName").value("amboji"));
//        verify(customerService,times(1)).getCustomerByLastName(lastName);
//    }
//    
//    
//    
//    @Test
//    public void testGetCustomerByFirstName() throws Exception{
//
//        String firstName="priyanka";
//        CustomerDto customerDtos=new CustomerDto();
//        customerDtos.setFirstName(firstName);
//        List<CustomerDto> customerList=Arrays.asList(customerDtos);
//        when(customerService.getCustomerByFirstName(firstName)).thenReturn(customerList);
//        mockmvc.perform(get("/customer/firstname/{fn}",firstName))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$[0].firstName").value("priyanka"));
//        verify(customerService,times(1)).getCustomerByFirstName(firstName);
//    }
//    
//    
//    @Test
//    public void testGetCustomerByEmail()throws Exception {
//      String email = "priyanka@gmail.com";
//      CustomerDto customerDto=new CustomerDto();
//      customerDto.setEmail(email);
//      when(customerService.getCustomerByEmail(email)).thenReturn(customerDto);
//      mockmvc.perform(get("/customer/email/{email}",email))
//      .andExpect(status().isOk())
//      .andExpect(jsonPath("$.email").value("priyanka@gmail.com"));
//      verify(customerService,times(1)).getCustomerByEmail(email);
//
//  }
//
//    @Test
//        public void testGetCustomerByCity() throws Exception {
//       String city = "hyderabad";
//       CustomerDto customerDto =new CustomerDto();
//       AddressDto addressDto =new AddressDto();
//       CityDto cityDto=new CityDto();
//       addressDto.setCityId(cityDto.getCityId());
//       customerDto.setAddressId(addressDto.getAddressId());
//       cityDto.setCity(city);
//       List<CustomerDto> customerList =Arrays.asList();
//       //System.out.println("-------------------------"+customerDto.getAddressId().getCityId().getCity());
//       when(customerService.getCustomerByCity(city)).thenReturn(customerList);
//        mockmvc.perform(MockMvcRequestBuilders.get("/staff/city/{city}", city))
//       .andExpect(MockMvcResultMatchers.status().isOk())
//       .andExpect(status().isOk())
//       . andExpect(jsonPath("$[0].addressId.cityId.city").value("hyderabad"));
//         verify(customerService,times(1)).getCustomerByCity(city);
//
//        }
//    
//    
//    
//    @Test
//    public void testGetCustomerByCountry() throws Exception{
//          String country="india";
//          
//          CustomerDto customerDto =new CustomerDto();
//          AddressDto addressDto =new AddressDto();
//         CityDto cityDto=new CityDto();
//         CountryDto countrydto=new CountryDto();
//         addressDto.setCityId(cityDto.getCityId());
//         customerDto.setAddressId(addressDto.getAddressId());
//         countrydto.setCountry(country);
//         //customerDto.getAddressId().getCityId().getCountryId().setCountry(country);
//         List<CustomerDto> customerList =Arrays.asList(customerDto);
//         when(customerService.getCustomerByCountry(country)).thenReturn(customerList);
//         mockmvc.perform(MockMvcRequestBuilders.get("/customer/country/{country}", country))
//       .andExpect(MockMvcResultMatchers.status().isOk())
//       .andExpect(status().isOk())
//       . andExpect(jsonPath("$[0].addressId.cityId.countryId.country").value("india"));
//       verify(customerService,times(1)).getCustomerByCountry(country);
//        }
//    
//    
//    
//    
//    @Test
//    public void testGetCustomerByPhone()throws Exception{
//          String phone="7657789";
//          CustomerDto customerDto =new CustomerDto();
//          AddressDto addressDto=new AddressDto();
//          customerDto.setAddressId(addressDto.getAddressId());
//          addressDto.setPhone(phone);
////          List<CustomerDto> customerList =Arrays.asList(customerDto);
//            when(customerService.getCustomerByPhone(phone)).thenReturn(customerDto);
//            mockmvc.perform(MockMvcRequestBuilders.get("/staff/phone/{phone}", phone))
//        .andExpect(status().isOk())
//        . andExpect(jsonPath("$[0].addressId.phone").value("7657789"));
//        verify(customerService,times(1)).getCustomerByPhone(phone);
//    }
//
//
//    
//    
//    
//    
//    
//    
//    
//    
//    
////@Test
////public void testGetCustomerByCity() throws Exception{
////
////  String city = "hyderabad";
////  List<CustomerDto> expectedCustomers = new ArrayList<>(); // Create a list of expected customers
////
////  when(customerService.getCustomerByCity(city)).thenReturn(expectedCustomers);
////  
////  ResponseEntity<List<CustomerDto>> response = customerController.getCustomerByCity(city);
////
////  assertEquals(HttpStatus.OK, response.getStatusCode());
////  assertEquals(expectedCustomers, response.getBody());
////  
////  verify(customerService).getCustomerByCity(eq(city));
////}
////
////
//  
////    @Test
////    void testGetCustomerAddressData() throws Exception {
////        
////        Short customerId = 1;
////        AddressDto expectedAddress = new AddressDto(/* provide necessary address details */);
////        when(customerService.getCustomerAddress(customerId)).thenReturn(expectedAddress);
////
////        ResponseEntity<AddressDto> response = customerController.getCustomerAddressData(customerId);
////
////        assertEquals(HttpStatus.OK, response.getStatusCode());
////        assertEquals(expectedAddress, response.getBody());
////    }
////    
////    
//
////    
////    @Test
////	void testGetCustomerByCountry() throws Exception{
////		
////		String country = "USA";
////		List<CustomerDto> customers = new ArrayList<>();
////		customers.add(new CustomerDto());
////		
////		when(customerService.getCustomerByCountry(country)).thenReturn(customers);
////		
////		ResponseEntity<List<CustomerDto>> response = customerController.getCustomerByCountry(country);
////		
////		assertNotNull(response);
////		assertEquals(HttpStatus.OK, response.getStatusCode());
////		assertEquals(customers, response.getBody());
////		verify(customerService).getCustomerByCountry(country);
////	}
////    
////    
////    @Test
////    public void testGetActiveCustomer() throws Exception {
////        
////        List<CustomerDto> expectedCustomers = new ArrayList<>();
////        expectedCustomers.add(new CustomerDto());
////
////        when(customerService.getAllActiveCustomer("1")).thenReturn(expectedCustomers);
////
////        ResponseEntity<List<CustomerDto>> response = customerController.getActiveCustomer();
////
////        assertEquals(HttpStatus.OK, response.getStatusCode());
////        assertEquals(expectedCustomers, response.getBody());
////        Mockito.verify(customerService).getAllActiveCustomer("1");
////    }
////    
////    
////    @Test
////    void testGetInactiveCustomer() throws Exception {
////        
////        List<CustomerDto> mockCustomers = new ArrayList<>();
////        when(customerService.getAllInactiveCustomer("0")).thenReturn(mockCustomers);
////        ResponseEntity<List<CustomerDto>> response = customerController.getInactiveCustomer();
////
////        assertEquals(HttpStatus.OK, response.getStatusCode());
////        assertEquals(mockCustomers, response.getBody());
////
////        verify(customerService, times(1)).getAllInactiveCustomer("0");
////        verifyNoMoreInteractions(customerService);
////    }
////    
////    
////    
////    
////    @Test
////    public void testGetCustomerByPhone() throws Exception {
////        String phone = "1234567890";
////        CustomerDto expectedCustomerDto = new CustomerDto();
////        
////        when(customerService.getCustomerByPhone(phone)).thenReturn(expectedCustomerDto);
////
////        ResponseEntity<CustomerDto> responseEntity = customerController.getCustomerByPhone(phone);
////
////        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
////        Assertions.assertEquals(expectedCustomerDto, responseEntity.getBody());
////
////        verify(customerService).getCustomerByPhone(phone);
////    }
//    
//    
//    
////    @Test
////    public void testUpdateCustomerLastName() throws NotFoundException {
//
////        short customerId = 1;
////        String newLastName = "Smith";
////        CustomerDto updatedCustomerDto = new CustomerDto();
////        updatedCustomerDto.setId(customerId);
////        updatedCustomerDto.setLastName(newLastName);
//        
////        when(customerService.updateCustomerLastName(customerId, newLastName)).thenReturn(updatedCustomerDto);
//
////        ResponseEntity<CustomerDto> response = customerController.updateCustomerLastName(customerId, newLastName);
//
////        verify(customerService).updateCustomerLastName(customerId, newLastName);
//
////        assertEquals(200, response.getStatusCodeValue());
////        assertEquals(updatedCustomerDto, response.getBody());
////    }
//    
//    
//    
////    @Test
////    public void testUpdateCustomerLastName() throws Exception {
//
////        CustomerDto updatedCustomerDto = new CustomerDto();
////        updatedCustomerDto.setId(1);
////        updatedCustomerDto.setLastName("Doe");
////        when(customerService.updateCustomerFirstName(anyShort(), anyString())).thenReturn(updatedCustomerDto);
////
//
////        String requestBody = "Doe";
////        String requestUrl = "/customer/update/ln/1";
////        mockMvc.perform(MockMvcRequestBuilders.put(requestUrl)
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(requestBody))
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(updatedCustomerDto.getId()))
////                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(updatedCustomerDto.getLastName()));
////    }
//    
//    
//    
////    @Test
////    void testUpdateCustomerEmail() throws NotFoundException {
//
////        short customerId = 123;
////        String newEmail = "new-email@example.com";
////
//
////        CustomerDto updatedCustomerDto = new CustomerDto();
////        updatedCustomerDto.setId(customerId);
////        updatedCustomerDto.setEmail(newEmail);
////        when(customerService.updateCustomerEmail(customerId, newEmail)).thenReturn(updatedCustomerDto);
//
////        ResponseEntity<CustomerDto> response = customerController.updateCustomerEmail(customerId, newEmail);
//
////        assertEquals(HttpStatus.OK, response.getStatusCode());
////        assertEquals(updatedCustomerDto, response.getBody());
////    }
//
//    
//    
//    
////    @Test
////    public void testUpdateCustomerStore() throws NotFoundException {
//
////        Short id = 1;
////        Short storeId = 2;
////        CustomerDto customerDto = new CustomerDto(); // Create a sample CustomerDto object
////        Mockito.when(customerService.updateCustomerStore(id, storeId)).thenReturn(customerDto);
//
////        ResponseEntity<CustomerDto> response = customerController.updateCustomerStore(id, storeId);
////        
////        Mockito.verify(customerService).updateCustomerStore(id, storeId);
////        assertEquals(HttpStatus.OK, response.getStatusCode());
////        assertEquals(customerDto, response.getBody());
////    }
////    
//    
//    
//    
////    @Test
////    public void testUpdateCustomerPhone() throws NotFoundException {
//
////        Short customerId = 1;
////        String newPhone = "1234567890";
////        CustomerDto updatedCustomerDto = new CustomerDto(customerId, newPhone);
////        when(customerService.updateCustomerPhone(customerId, newPhone)).thenReturn(updatedCustomerDto);
//    
////        ResponseEntity<CustomerDto> response = customerController.updateCustomerPhone(customerId, newPhone);
//
////        verify(customerService).updateCustomerPhone(customerId, newPhone);
////        assertEquals(HttpStatus.OK, response.getStatusCode());
////        assertEquals(updatedCustomerDto, response.getBody());
////    }
////
////    
////    }
//
//
//
//}
//
//    
//   
//
//
//
