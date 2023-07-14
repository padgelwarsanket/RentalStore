package com.sprint.filmerentalstore.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "film")
public class Film implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_id")
	private Short filmId;

	@Column(name = "title", nullable = false, length = 255)
	private String title;

	@Lob
	@Column(name = "description")
	private String description;

	@Column(name = "release_year", length = 4)
	private String releaseYear;

	@Column(name = "rental_duration")
	private Integer rentalDuration;

	@Column(name = "rental_rate")
	private Double rentalRate;

	@Column(name = "length")
	private Integer length;

	@Column(name = "replacement_cost")
	private Double replacementCost;

	@Column(name = "rating", length = 10)
	private String rating;

	@Column(name = "special_features")
	private String specialFeatures;

	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;

	@ManyToOne
	@JoinColumn(name = "original_language_id")
	private Language originalLanguage;
//	
	@JsonFormat
	@Column(name = "last_update")
	private LocalDateTime lastUpdate;
//	@OneToMany
//	@JoinTable(name = "film_actors", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
//	private List<FilmActor> filmActors = new ArrayList<>();
////
//	@OneToMany
//	@JoinTable(name = "film_categories", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
//	private List<FilmCategory> filmCategories = new ArrayList<>();

	@OneToMany(mappedBy = "filmId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<FilmCategory> filmCategories = new ArrayList<>();
	
	@OneToMany(mappedBy = "filmId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<FilmActor> filmActors = new ArrayList<>();

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", description=" + description + ", releaseYear="
				+ releaseYear + ", rentalDuration=" + rentalDuration + ", rentalRate=" + rentalRate + ", length="
				+ length + ", replacementCost=" + replacementCost + ", rating=" + rating + ", specialFeatures="
				+ specialFeatures + ", language=" + language + ", originalLanguage=" + originalLanguage
				+ ", lastUpdate=" + lastUpdate + "]";
	}

	
}
