package it.uniroma3.siw.easyCrag.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Ripetizione {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private User scalatore;
	@ManyToOne
	private Via viaScalata;
	@Min(0)
	@Max(5)
	private Float votoAssegnato;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getScalatore() {
		return scalatore;
	}
	public void setScalatore(User scalatore) {
		this.scalatore = scalatore;
	}
	public Via getViaScalata() {
		return viaScalata;
	}
	public void setViaScalata(Via viaScalata) {
		this.viaScalata = viaScalata;
	}
	public Float getVotoAssegnato() {
		return votoAssegnato;
	}
	public void setVotoAssegnato(Float votoAssegnato) {
		this.votoAssegnato = votoAssegnato;
	}
	
	
}
