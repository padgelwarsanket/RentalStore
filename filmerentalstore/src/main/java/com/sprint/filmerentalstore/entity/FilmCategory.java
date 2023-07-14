
package com.sprint.filmerentalstore.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "film_category")
@IdClass(FilmCategoryId.class)
public class FilmCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category categoryId;

	@Id
	@ManyToOne
	@JoinColumn(name = "film_id")
	private Film filmId;

	@JsonFormat
	@Column(name = "last_update")
	private LocalDateTime lastUpdate;

	@Override
	public String toString() {
		return "FilmCategory [categoryId=" + categoryId + ", filmId=" + filmId + ", lastUpdate=" + lastUpdate + "]";
	}

}

class FilmCategoryId {

	private Film filmId;
	private Category categoryId;

	public FilmCategoryId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilmCategoryId(Film filmId, Category categoryId) {
		super();
		this.filmId = filmId;
		this.categoryId = categoryId;
	}

	public Film getFilmId() {
		return filmId;
	}

	public void setFilmId(Film filmId) {
		this.filmId = filmId;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId, filmId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmCategoryId other = (FilmCategoryId) obj;
		return Objects.equals(categoryId, other.categoryId) && Objects.equals(filmId, other.filmId);
	}

}
