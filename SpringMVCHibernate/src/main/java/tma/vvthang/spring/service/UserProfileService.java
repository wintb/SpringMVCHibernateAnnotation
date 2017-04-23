package tma.vvthang.spring.service;

import java.util.List;

import tma.vvthang.spring.model.UserProfile;

public interface UserProfileService {
	UserProfile findByType(String type);
	UserProfile findById(int id);
	List<UserProfile> findAll();
}
