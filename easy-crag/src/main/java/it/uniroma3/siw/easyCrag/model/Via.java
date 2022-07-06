package it.uniroma3.siw.easyCrag.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Via {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String nome;
	
	@Min(1)
	@Max(10)
	private Integer difficolta;
	
	@Min(0)
	@Max(5)
	private Float votoMedio;
	@ManyToOne
	private Falesia falesia;
	@OneToMany(mappedBy = "viaScalata", cascade = CascadeType.REMOVE)
	private List<Ripetizione> ripetizioni;
	
	public List<Ripetizione> getRipetizioni() {
		return ripetizioni;
	}
	public void setRipetizioni(List<Ripetizione> ripetizioni) {
		this.ripetizioni = ripetizioni;
	}
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
	public Integer getDifficolta() {
		return difficolta;
	}
	public void setDifficolta(int difficolta) {
		this.difficolta = difficolta;
	}
	public Falesia getFalesia() {
		return falesia;
	}
	public void setFalesia(Falesia falesia) {
		this.falesia = falesia;
	}
	public Float getVotoMedio() {
		return votoMedio;
	}
	public void setVotoMedio(Float votoMedio) {
		this.votoMedio = votoMedio;
	}
	
}
