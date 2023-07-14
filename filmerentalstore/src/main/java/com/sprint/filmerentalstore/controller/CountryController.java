package com.sprint.filmerentalstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.filmerentalstore.dto.CountryDto;
import com.sprint.filmerentalstore.service.CountryService;


@RestController
@RequestMapping("/country")
public class CountryController {
	
	@Autowired
	private CountryService service;
	
	@GetMapping
	public ResponseEntity<List<CountryDto>> getEmployee(){
		List<CountryDto> employeeByID = service.getAllCountry();
		System.out.println(employeeByID);
		return new ResponseEntity<List<CountryDto>>(employeeByID,HttpStatus.FOUND);
	}

}
