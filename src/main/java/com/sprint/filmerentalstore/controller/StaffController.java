package com.sprint.filmerentalstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.filmerentalstore.dto.StaffDto;
import com.sprint.filmerentalstore.entity.Staff;
import com.sprint.filmerentalstore.service.StaffService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private StaffService service;
	
	
	
	@PutMapping("/update/{staffId}")

    public ResponseEntity<StaffDto> updatestorePhoneNumber(@PathVariable Short staffId, @RequestBody StaffDto staff) {

        System.out.println(staff.toString());

        StaffDto dto = service.updateStaff(staffId, staff);

        return ResponseEntity.ok(dto);

    }
	
//	http://localhost:9090/api/staff
	@GetMapping
	public ResponseEntity<List<StaffDto>> getAllStaff(){
		List<StaffDto> staffDto = service.getAllStaff();
		return new ResponseEntity<List<StaffDto>>(staffDto,HttpStatus.OK);	
	}
	
//	http://localhost:9090/api/staff
    @PostMapping
    public ResponseEntity<StaffDto> addStaff(@Valid @RequestBody StaffDto staffDto) {
    	StaffDto dto = service.addStaff(staffDto);
        return new ResponseEntity<StaffDto>(dto,HttpStatus.CREATED);
    }
    
//    http://localhost:9090/api/staff/lastname/Macky
    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<StaffDto>> getStaffByLastName(@PathVariable String lastName) {
        List<StaffDto> staffList = service.getStaffByLastName(lastName);
        return new ResponseEntity<>(staffList, HttpStatus.OK);
    }
    
//    http://localhost:9090/api/staff/fn/Jon
    @GetMapping("/fn/{firstName}")
    public ResponseEntity<List<StaffDto>> getStaffByFirstName(@PathVariable String firstName) {
        List<StaffDto> staffList = service.getStaffByFirstName(firstName);
        return new ResponseEntity<>(staffList, HttpStatus.OK);
    }

//    http://localhost:9090/api/staff/email/Jony.Stephens@sakilastaff.com
	@GetMapping("/email/{email}")
	public ResponseEntity<List<StaffDto>> getStaffByEmail(@PathVariable String email) {
		List<StaffDto> staffList = service.getStaffByEmail(email);
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}
	
//	http://localhost:9090/api/staff/2/address
    @PutMapping("/{staffId}/address")
    public ResponseEntity<StaffDto> assignAddressToStaff(@PathVariable Short staffId, @Valid @RequestBody Staff staff) {
        StaffDto staffAddress = service.updateStaffAddress(staffId, staff.getAddressId().getAddressId());
        return ResponseEntity.ok(staffAddress);

    }
    
//    http://localhost:9090/api/staff/city/Lethbridge
    @GetMapping("/city/{city}")
	public ResponseEntity<List<StaffDto>> getStaffByCity(@PathVariable String city) {
		List<StaffDto> staffList = service.getStaffByCity(city);
		return new ResponseEntity<>(staffList, HttpStatus.OK);
	}
  
//    http://localhost:9090/api/staff/country/Canada
    @GetMapping("/country/{country}")
   	public ResponseEntity<List<StaffDto>> getStaffByCountry(@PathVariable String country) {
   		List<StaffDto> staffList = service.getStaffByCountry(country);
   		return new ResponseEntity<>(staffList, HttpStatus.OK);
   	}
    
//    http://localhost:9090/api/staff/phone/14033335568 , 6172235589
    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<StaffDto>> getStaffByPhone(@PathVariable String phone) {
        List<StaffDto> dtos = service.getStaffByPhone(phone);
        return ResponseEntity.ok(dtos);
    }
    
//   http://localhost:9090/api/staff/update/firstname/2 
    @PutMapping("/update/firstname/{staffId}")
    public ResponseEntity<StaffDto> updateStaffFirstName(@PathVariable Short staffId,@Valid @RequestBody Staff staff) {
        StaffDto dto = service.updateStaffFirstName(staffId,staff.getFirstName());
        return ResponseEntity.ok(dto);
    }

//    http://localhost:9090/api/staff/update/lastname/2
    @PutMapping("update/lastname/{staffId}")
    public ResponseEntity<StaffDto> updateStaffLastName(@PathVariable Short staffId,@Valid @RequestBody Staff staff) {
        StaffDto dto = service.updateStaffLastName(staffId,staff.getLastName());
        return ResponseEntity.ok(dto);
    }

//    http://localhost:9090/api/staff/update/email/2
    @PutMapping("update/email/{staffId}")
    public ResponseEntity<StaffDto> updateStaffEmail(@PathVariable Short staffId,@Valid @RequestBody Staff staff) {
       StaffDto dto = service.updateStaffEmail(staffId, staff.getEmail());
        return ResponseEntity.ok(dto);
    }

//    http://localhost:9090/api/staff/update/store/2
    @PutMapping("update/store/{staffId}")
    public ResponseEntity<StaffDto> assignStoreToStaff(@PathVariable Short staffId,@Valid @RequestBody Staff staff) {
        StaffDto dto =service.updateStoreToStaff(staffId, staff.getStoreId().getStoreId());
        return ResponseEntity.ok(dto);
    }

//    http://localhost:9090/api/staff/update/phone/2
    @PutMapping("update/phone/{staffId}")
    public ResponseEntity<StaffDto> updateStaffPhoneNumber(@PathVariable Short staffId,@Valid @RequestBody Staff staff) {
        StaffDto dto = service.updateStaffPhoneNumber(staffId, staff.getAddressId().getPhone());
        return ResponseEntity.ok(dto);
 
    
}

   }


