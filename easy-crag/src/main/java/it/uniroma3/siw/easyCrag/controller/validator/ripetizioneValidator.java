package it.uniroma3.siw.easyCrag.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.easyCrag.model.Ripetizione;
import it.uniroma3.siw.easyCrag.services.RipetizioneService;
@Component
public class ripetizioneValidator implements Validator {
	@Autowired
	RipetizioneService ripetizioneService;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Ripetizione.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Ripetizione ripetizione = (Ripetizione) target;
		if(ripetizioneService.alreadyExists(ripetizione)) {
			errors.reject("ripetizione.duplicata");
		}
		
	}

}
