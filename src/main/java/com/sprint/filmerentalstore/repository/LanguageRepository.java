package com.sprint.filmerentalstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.filmerentalstore.entity.Language;


public interface LanguageRepository  extends JpaRepository<Language, Byte>{
	
		Optional<Language> findByName(String name);
	
}
