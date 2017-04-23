package tma.vvthang.spring.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import tma.vvthang.spring.model.User;
import tma.vvthang.spring.model.UserProfile;
import tma.vvthang.spring.service.UserProfileService;
import tma.vvthang.spring.service.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	MessageSource messageSource;
	
	//This method will list all existing users
	@RequestMapping(value = {"/", "list"}, method = RequestMethod.GET)
	public String listUsers(ModelMap model){
		List<User> users = userService.findAllUser();
		model.addAttribute("users", users);
		return "userslist";
	}
	
	@RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
	public String newUser(ModelMap model){
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "registration";
	}
	
	@RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model){
		if(result.hasErrors()){
			return "registration";
		}
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId", 
					new String[]{user.getSsoId()}, Locale.getDefault()));
			result.addError(ssoError);
			return "registration";
		}
		
		userService.saveUser(user);
		model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
		return "registrationsuccess";
	}
	
	@RequestMapping(value = {"/edit-user-{ssoId}"}, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model){
		User user = userService.findBySSOID(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	@RequestMapping(value = {"/edit-user-{ssoId}"}, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String ssoId){
		if(result.hasErrors()){
			return "registration";
		}
		
		userService.updateUser(user);
		
		model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " update successfully");
		return "registrationsuccess";
	}
	
	@RequestMapping(value = {"/delete-user-{ssoId}"}, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId){
		userService.deleteUserBySSOID(ssoId);
		return "redirect:/list";
	}
	
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles(){
		return userProfileService.findAll();
	}
	
	
	
	
	
	
	
	
	
	
}
