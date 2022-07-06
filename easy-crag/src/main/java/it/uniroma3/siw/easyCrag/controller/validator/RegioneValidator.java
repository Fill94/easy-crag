package it.uniroma3.siw.easyCrag.controller.validator;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.easyCrag.model.Regione;
import it.uniroma3.siw.easyCrag.model.RegioneEnum;
import it.uniroma3.siw.easyCrag.services.RegioneService;
@Component
public class RegioneValidator implements Validator{
	@Autowired
	private RegioneService regioneService;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Regione.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Regione regione = (Regione)	target;
		if(regioneService.alreadyExists(regione)) {
			errors.reject("regione.duplicata");
		}
		
	}

}
