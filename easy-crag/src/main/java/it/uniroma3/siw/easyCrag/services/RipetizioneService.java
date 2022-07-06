package it.uniroma3.siw.easyCrag.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.uniroma3.siw.easyCrag.model.Ripetizione;
import it.uniroma3.siw.easyCrag.model.User;
import it.uniroma3.siw.easyCrag.model.Via;
import it.uniroma3.siw.easyCrag.repository.RipetizioneRepository;
import net.bytebuddy.asm.Advice.This;

@Service
public class RipetizioneService {
	@Autowired
	private RipetizioneRepository ripetizioneRepository;
	@Transactional
	public void save(Ripetizione ripetizione) {
		// TODO Auto-generated method stub
		ripetizioneRepository.save(ripetizione);
	}

	public Float calcolaVotoMedio(Via via) {
		List<Ripetizione> ripetizioni =this.ripetizioneRepository.findByViaScalata(via);
		Float sommaVoti = 0.0f;
		if(ripetizioni.size()==0) {
			return sommaVoti;
		}
		for(Ripetizione ripetizione:ripetizioni) {
			sommaVoti = sommaVoti+ripetizione.getVotoAssegnato();
		}
		Float mediaVoti = sommaVoti/(ripetizioni.size());
		return mediaVoti;
	}

	public void remove(Long idRipetizione) {
		// TODO Auto-generated method stub
		this.ripetizioneRepository.deleteById(idRipetizione);
	}

	public Ripetizione findByScalatore(User user) {
		return ripetizioneRepository.findByScalatore(user);
	}

	public Ripetizione findById(Long idRipetizione) {
		// TODO Auto-generated method stub
		return ripetizioneRepository.findById(idRipetizione).get();
	}

	public boolean alreadyExists(Ripetizione ripetizione) {
		// TODO Auto-generated method stub
		return ripetizioneRepository.existsByViaScalataAndScalatore(ripetizione.getViaScalata(), ripetizione.getScalatore());
	}

}
