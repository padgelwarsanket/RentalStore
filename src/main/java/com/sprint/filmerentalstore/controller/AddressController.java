package com.sprint.filmerentalstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.filmerentalstore.dto.AddressDto;
import com.sprint.filmerentalstore.entity.Address;
import com.sprint.filmerentalstore.serviceimpl.AddressServiceImpl;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	
		
		@Autowired
		private AddressServiceImpl service;;
		
		@GetMapping
		public ResponseEntity<List<AddressDto>> getAllAddress(){
			List<AddressDto> entity = service.getAllAddress();
			return new ResponseEntity<List<AddressDto>>(entity,HttpStatus.FOUND);
		}
		
		@GetMapping("/id/{id}")
		public ResponseEntity<AddressDto> getEmployee(@PathVariable Short id){
			AddressDto entity = service.getAddressById(id);
			return new ResponseEntity<AddressDto>(entity,HttpStatus.FOUND);
		}


}