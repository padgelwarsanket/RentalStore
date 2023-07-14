package com.sprint.filmerentalstore.dto;

import lombok.Data;

@Data

public class CustomDto {
	private Short id;
	private String firstName;
	private Integer filmCount;
	public CustomDto(Short id, String firstName, Integer filmCount) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.filmCount = filmCount;
	}
	
	

}