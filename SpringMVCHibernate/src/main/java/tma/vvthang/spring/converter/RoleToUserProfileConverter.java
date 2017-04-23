package tma.vvthang.spring.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tma.vvthang.spring.model.UserProfile;
import tma.vvthang.spring.service.UserProfileService;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile>{

	
	@Autowired
	UserProfileService userProfileService;
	
	public UserProfile convert(Object element) {
		Integer id = Integer.parseInt((String)element);
		UserProfile profile = userProfileService.findById(id);
		System.out.println("Profile: " + profile);
		return profile;
	}
	
}
