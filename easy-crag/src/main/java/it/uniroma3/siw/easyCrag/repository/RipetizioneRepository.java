package it.uniroma3.siw.easyCrag.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.easyCrag.model.Ripetizione;
import it.uniroma3.siw.easyCrag.model.User;
import it.uniroma3.siw.easyCrag.model.Via;

public interface RipetizioneRepository extends CrudRepository<Ripetizione, Long>{

	public List<Ripetizione> findByViaScalata(Via via);

	public Ripetizione findByScalatore(User user);

	public boolean existsByViaScalataAndScalatore(Via viaScalata, User scalatore);

}
