package it.uniroma3.siw.easyCrag.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.easyCrag.model.Falesia;
import it.uniroma3.siw.easyCrag.model.Regione;

public interface FalesiaRepository extends CrudRepository<Falesia, Long> {

	List<Falesia> findByRegione(Regione regione);

	boolean existsByNomeAndRegione(String nome, Regione regione);

	

	
	
}
