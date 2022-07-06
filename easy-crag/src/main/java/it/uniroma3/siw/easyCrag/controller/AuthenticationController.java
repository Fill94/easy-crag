package it.uniroma3.siw.easyCrag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.easyCrag.controller.validator.CredentialsValidator;
import it.uniroma3.siw.easyCrag.controller.validator.UserValidator;
import it.uniroma3.siw.easyCrag.model.Credentials;
import it.uniroma3.siw.easyCrag.model.User;
import it.uniroma3.siw.easyCrag.services.CredentialsService;
import it.uniroma3.siw.easyCrag.services.FalesiaService;
import it.uniroma3.siw.easyCrag.services.RegioneService;
import net.bytebuddy.asm.Advice.This;
@Controller
public class AuthenticationController {
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private RegioneService regioneService;
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private CredentialsValidator credentialsValidator;
	
	private static final int ADMIN_CODE = 1234;
	@GetMapping(value = "/register")
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "registerUser.html";
	}
	
	@GetMapping(value = "/login")
	public String showLoginForm (Model model) {
		return "loginForm.html";
	}
	
	@GetMapping(value = "/logout")
	public String logout(Model model) {
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		model.addAttribute("regioni",this.regioneService.findAll());
		return "index.html";
	}
	
   @GetMapping(value = "/default")
    public String defaultAfterLogin(Model model) {
        
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            model.addAttribute("regioni", regioneService.findAll());
    		return "admin/adminPage.html";
        }
    	if(credentials.getRole().equals(Credentials.DEFAULT_ROLE)) {
    		User user = credentials.getUser();
    		model.addAttribute("ripetizioni", user.getRipetizioni());
    		return "user/userPage.html";
    	}
        return "index.html";
    }
	
    @PostMapping(value = "/register")
    public String registerUser(@ModelAttribute("user") User user,
                 BindingResult userBindingResult,
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {

        // validate user and credentials fields
        this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            credentials.setUser(user);
            if(credentials.getAdminCode() == ADMIN_CODE)
            	credentialsService.saveAdminCredentials(credentials);
            else
            	credentialsService.saveUserCredentials(credentials);
            return "registrationSuccessful.html";
        }
        return "registerUser.html";
    }
}
