package co.com.innovaschools.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "account")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	// @NotEmpty(message = "El id no puede estar vacio")
//	@NotNull(message = "El id no puede ser nulo")
	@Id
	private int id;

	// @NotEmpty(message = "La activacion de la tarjeta puede estar vacio")
	@NotNull(message = "La activacion de la tarjeta no puede ser nulo")
	private boolean active_cards;

	// @NotEmpty(message = "El limite disponible no puede estar vacio")
	// @NotNull(message = "Ell limite disponible no puede ser nulo")
	private int limit_available;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive_cards() {
		return active_cards;
	}

	public void setActive_cards(boolean active_cards) {
		this.active_cards = active_cards;
	}

	public int getLimit_available() {
		return limit_available;
	}

	public void setLimit_available(int limit_available) {
		this.limit_available = limit_available;
	}

}
