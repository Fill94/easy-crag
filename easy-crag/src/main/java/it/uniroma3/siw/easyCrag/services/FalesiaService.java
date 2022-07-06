package it.uniroma3.siw.easyCrag.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.easyCrag.model.Falesia;
import it.uniroma3.siw.easyCrag.model.Regione;
import it.uniroma3.siw.easyCrag.repository.FalesiaRepository;
@Service
public class FalesiaService {
	@Autowired
	private FalesiaRepository falesiaRepository;
	@Transactional
	public void save(Falesia falesia) {
		this.falesiaRepository.save(falesia);
	}
	public List<Falesia> findByRegione(Regione regione){
		return this.falesiaRepository.findByRegione(regione);
	}
	public Falesia findById(Long idFalesia) {
		// TODO Auto-generated method stub
		return this.falesiaRepository.findById(idFalesia).get();
	}
	public boolean alreadyExists(Falesia falesia) {
		// TODO Auto-generated method stub
		return this.falesiaRepository.existsByNomeAndRegione(falesia.getNome(), falesia.getRegione());
	}
	public void removeFalesia(Long idFalesia) {
		this.falesiaRepository.deleteById(idFalesia);
	}	
}
