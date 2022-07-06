package it.uniroma3.siw.easyCrag.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.easyCrag.model.Regione;

public interface RegioneRepository extends CrudRepository<Regione, Long> {

	boolean existsByNome(String nomeRegione);

}
