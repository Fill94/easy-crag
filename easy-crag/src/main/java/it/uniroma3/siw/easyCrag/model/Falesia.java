package it.uniroma3.siw.easyCrag.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
public class Falesia {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String descrizione;
	@ManyToOne
	private Regione regione;
	@OneToMany(mappedBy = "falesia", cascade = CascadeType.REMOVE)
	private List<Via> vie;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Regione getRegione() {
		return regione;
	}
	public void setRegione(Regione regione) {
		this.regione = regione;
	}
	public List<Via> getVie() {
		return vie;
	}
	public void setVie(List<Via> vie) {
		this.vie = vie;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
