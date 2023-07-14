package com.sprint.filmerentalstore.exception;

public class FilmRentalStoreException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FilmRentalStoreException(String message){
		super(message);
	}
	
	public FilmRentalStoreException() {
		super();
	}

}
