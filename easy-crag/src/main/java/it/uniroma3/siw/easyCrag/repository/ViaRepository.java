package it.uniroma3.siw.easyCrag.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.easyCrag.model.Falesia;
import it.uniroma3.siw.easyCrag.model.Via;

public interface ViaRepository extends CrudRepository<Via, Long> {

	boolean existsByNomeAndFalesia(String nome, Falesia falesia);
	
}
