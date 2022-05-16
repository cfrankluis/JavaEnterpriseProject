package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import application.model.Confirmation;
import application.model.User;
import application.service.ConfirmationService;
import application.service.EmailService;
import application.service.UserService;

@RestController
public class RegesterController {
	
	private UserService userService;
	private ConfirmationService confirmation;
	private EmailService emailService;
	private ConfirmationService confirmationService;
	
	
	@Autowired
	public RegesterController(UserService userService, ConfirmationService confirmation, EmailService emailService, ConfirmationService confirmationService ) {
		this.userService = userService;
		this.confirmation = confirmation;
		this.emailService = emailService;
		this.confirmationService = confirmationService;
	}
	
//	@GetMapping(value="/register")
//	public ModelAndView displayRegistration(HttpSession session, User user)
//	{
//		session.setAttribute("user", user);
//		session.setViewName("register");
//		return session;
//	}
	
	@PostMapping(value="/register")
	public void sendEmailLink(@RequestBody User sentUser) {
		Confirmation confirmationToken = new Confirmation(sentUser);
		confirmationService.createConfirmationToken(confirmationToken);
		
		

		String toEmail = sentUser.getEmail();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("socialmedianow63@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
        +"http://localhost:9022/confirm-account/?token="+confirmationToken.getConfirmationToken());

        emailService.sendEmail(mailMessage);
	}	
	
	@RequestMapping(value="/confirm-account/*", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken){

		Confirmation token = confirmationService.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
        	User user = userService.getUserByEmail(token.getUser().getEmail());
            System.out.println(user);
        	user.setConfirmed(true);
        	System.out.println(user.isConfirmed());
        	userService.createUser(user);
            
//            modelAndView.setViewName("index.html");
            System.out.println("it worked");
            
            //set conformationtoken to null
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
	
	

	    
	    
	    
//	    
//
//	    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
//	    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
//	    {
//	        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
//
//	        if(token != null)
//	        {
//	        	UserEntity user = userRepository.findByEmailIdIgnoreCase(token.getUserEntity().getEmailId());
//	            user.setEnabled(true);
//	            userRepository.save(user);
//	            modelAndView.setViewName("accountVerified");
//	        }
//	        else
//	        {
//	            modelAndView.addObject("message","The link is invalid or broken!");
//	            modelAndView.setViewName("error");
//	        }
//
//	        return modelAndView;
//	    }
//	}

}

//	    @RequestMapping(value="/register", method = RequestMethod.POST)
//	    public ModelAndView registerUser(ModelAndView modelAndView, UserEntity userEntity)
//	    {
//
//	    	UserEntity existingUser = userRepository.findByEmailIdIgnoreCase(userEntity.getEmailId());
//	        if(existingUser != null)
//	        {
//	            modelAndView.addObject("message","This email already exists!");
//	            modelAndView.setViewName("error");
//	        }
//	        else
//	        {
//	            userRepository.save(userEntity);
//
//	            ConfirmationToken confirmationToken = new ConfirmationToken(userEntity);
//
//	            confirmationTokenRepository.save(confirmationToken);
//
//	            SimpleMailMessage mailMessage = new SimpleMailMessage();
//	            mailMessage.setTo(userEntity.getEmailId());
//	            mailMessage.setSubject("Complete Registration!");
//	            mailMessage.setFrom("YOUR EMAIL ADDRESS");
//	            mailMessage.setText("To confirm your account, please click here : "
//	            +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
//
//	            emailService.sendEmail(mailMessage);
//
//	            modelAndView.addObject("emailId", userEntity.getEmailId());
//
//	            modelAndView.setViewName("successfulRegisteration");
//	        }
//
//	        return modelAndView;
//	    }