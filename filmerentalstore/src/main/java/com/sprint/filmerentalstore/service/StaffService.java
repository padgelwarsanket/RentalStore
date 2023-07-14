package com.sprint.filmerentalstore.service;

import java.util.List;
import java.util.Optional;

import com.sprint.filmerentalstore.dto.StaffDto;

public interface StaffService {

		public List<StaffDto> getAllStaff() ;
		
		public StaffDto addStaff(StaffDto staffDto);
	
	    public List<StaffDto> getStaffByLastName(String lastName);
	    
	    public List<StaffDto> getStaffByFirstName(String firstName);
	    
	    public List<StaffDto> getStaffByEmail(String email);
	    
	    public StaffDto updateStaffAddress(Short staffId, Short addressId);
	    
	    public List<StaffDto> getStaffByCity(String city);

		public List<StaffDto> getStaffByCountry(String country);

		public List<StaffDto> getStaffByPhone(String phone);

		public StaffDto updateStaffFirstName(Short staffId, String firstName);

		public StaffDto updateStaffLastName(Short staffId, String lastName);

		public StaffDto updateStaffEmail(Short staffId, String email);

		public StaffDto updateStoreToStaff(Short staffId, Short storeId);

		public StaffDto updateStaffPhoneNumber(Short staffId, String phone);

		public StaffDto updateStaff(Short staffId, StaffDto staff);

	    
//	    List<StaffDto> getStaffByCountry(String country);

//	    void updateStaffFirstName(Long staffId, String firstName);
//	    void updateStaffLastName(Long staffId, String lastName);
//	    void updateStaffEmail(Long staffId, String email);
//	    void assignStoreToStaff(Long staffId, Long storeId);
//	    void updateStaffPhoneNumber(Long staffId, String phoneNumber);

		 
//		 
//		 public EmployeeDto updateEmployee(Integer id, EmployeeDto employeeDto);

}
