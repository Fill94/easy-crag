package it.uniroma3.siw.easyCrag.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.easyCrag.model.Via;
import it.uniroma3.siw.easyCrag.services.ViaService;

@Component
public class ViaValidator implements Validator {
	@Autowired
	private ViaService viaService;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Via.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Via via = (Via) target;
		if(viaService.alreadyExists(via))
			errors.reject("via.duplicata");
		
	}

}
