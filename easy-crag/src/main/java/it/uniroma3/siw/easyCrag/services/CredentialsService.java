package it.uniroma3.siw.easyCrag.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.easyCrag.model.Credentials;
import it.uniroma3.siw.easyCrag.repository.CredentialsRepository;
@Service
public class CredentialsService {
	@Autowired
	private CredentialsRepository credRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public Credentials getCredentials(Long id) {
		Optional<Credentials> result = this.credRepo.findById(id);
		return result.orElse(null);
	}
	@Transactional
	public Credentials getCredentials(String username) {
		// TODO Auto-generated method stub
		Optional<Credentials> result = this.credRepo.findByUsername(username);
		return result.orElse(null);
	}
	@Transactional
	public void saveAdminCredentials(Credentials credentials) {
		credentials.setRole(Credentials.ADMIN_ROLE);
		credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
		this.credRepo.save(credentials);
	}
	public void saveUserCredentials(Credentials credentials) {
		credentials.setRole(Credentials.DEFAULT_ROLE);
		credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
		this.credRepo.save(credentials);
		
	}
}
