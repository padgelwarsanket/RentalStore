package com.sprint.filmerentalstore.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "film_actor")
@IdClass(FilmActorId.class)
public class FilmActor implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name ="film_id")
//	private short filmIdpri;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actor_id")
	private Actor actorId;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "film_id")
	private Film filmId;

	
	@Column(name = "last_update")
	private LocalDateTime lastUpdate;

	@Override
	public String toString() {
		return "FilmActor [actorId=" + actorId + ", lastUpdate=" + lastUpdate + "]";
	}

}

class FilmActorId {

	private Film filmId;
	private Actor actorId;

	public FilmActorId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilmActorId(Film filmId, Actor actorId) {
		super();
		this.filmId = filmId;
		this.actorId = actorId;
	}

	public Film getFilmId() {
		return filmId;
	}

	public void setFilmId(Film filmId) {
		this.filmId = filmId;
	}

	public Actor getActorId() {
		return actorId;
	}

	public void setActorId(Actor actorId) {
		this.actorId = actorId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(filmId, actorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmActorId other = (FilmActorId) obj;
		return Objects.equals(filmId, other.filmId) && Objects.equals(actorId, other.actorId);
	}

}
