package it.uniroma3.siw.easyCrag.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.easyCrag.model.Via;
import it.uniroma3.siw.easyCrag.repository.ViaRepository;

@Service
public class ViaService {
	@Autowired
	private ViaRepository viaRepository;
	@Transactional
	public void save(Via via) {
		// TODO Auto-generated method stub
		viaRepository.save(via);
	}
	public Via findById(Long idVia) {
		// TODO Auto-generated method stub
		return viaRepository.findById(idVia).get();
	}
	public boolean alreadyExists(Via via) {
		// TODO Auto-generated method stub
		return this.viaRepository.existsByNomeAndFalesia(via.getNome(), via.getFalesia());
	}
	public void remove(Long idVia) {
		this.viaRepository.deleteById(idVia);
	}
	

}
