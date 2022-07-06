package it.uniroma3.siw.easyCrag.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.easyCrag.model.Falesia;
import it.uniroma3.siw.easyCrag.services.FalesiaService;
@Component
public class FalesiaValidator implements Validator{
	@Autowired
	private FalesiaService falesiaService;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Falesia.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Falesia falesia =(Falesia) target;
		if(falesiaService.alreadyExists(falesia))
			errors.reject("falesia.duplicata");
		
	}

}
