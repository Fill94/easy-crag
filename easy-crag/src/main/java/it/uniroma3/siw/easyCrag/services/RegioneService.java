package it.uniroma3.siw.easyCrag.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.easyCrag.model.Regione;
import it.uniroma3.siw.easyCrag.model.RegioneEnum;
import it.uniroma3.siw.easyCrag.repository.RegioneRepository;

@Service
public class RegioneService {
	@Autowired
	private RegioneRepository regioneRepository;

	public Iterable<Regione> findAll(){
		return this.regioneRepository.findAll();
	}
	@Transactional
	public void saveRegione(Regione regione) {
		// TODO Auto-generated method stub
		this.regioneRepository.save(regione);
	}

	public boolean alreadyExists(Regione regione) {
		// TODO Auto-generated method stub
		return this.regioneRepository.existsByNome(regione.getNome());
	}
	public List<String> getNomiRegioni(){
		List<String> nomiRegioni = new ArrayList<String>();
		for(RegioneEnum regione : RegioneEnum.values()) {
			nomiRegioni.add(regione.toString());
		}
		return nomiRegioni;
	}
	public Regione findById(Long id) {
		return this.regioneRepository.findById(id).get();
	}
	@Transactional
	public void remove(Long idRegione) {
		// TODO Auto-generated method stub
		this.regioneRepository.deleteById(idRegione);
	}
}
