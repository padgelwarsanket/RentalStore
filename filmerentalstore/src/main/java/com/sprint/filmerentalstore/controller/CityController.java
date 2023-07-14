package com.sprint.filmerentalstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.filmerentalstore.dto.CityDto;
import com.sprint.filmerentalstore.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController{
	
	@Autowired
	private CityService service;;
	
	@GetMapping
	public ResponseEntity<List<CityDto>> getEmployee(){
		List<CityDto> entity = service.getAllCity();
		return new ResponseEntity<List<CityDto>>(entity,HttpStatus.FOUND);
	}

}
