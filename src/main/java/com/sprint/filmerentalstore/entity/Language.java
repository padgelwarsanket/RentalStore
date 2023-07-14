package com.sprint.filmerentalstore.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "language")
public class Language implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "language_id")
	private Byte languageId;
	
	@Column(name = "name", nullable = false)
	private String name;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
	@Column(name = "last_update")
	private LocalDateTime lastUpdate;

//	@OneToMany(mappedBy = "language")
//	private List<Film> films;

}
