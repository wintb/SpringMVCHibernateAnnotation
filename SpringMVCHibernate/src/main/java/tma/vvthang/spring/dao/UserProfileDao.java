package tma.vvthang.spring.dao;

import java.util.List;

import tma.vvthang.spring.model.UserProfile;

public interface UserProfileDao {
	UserProfile findByType(String type);
	UserProfile findById(int id);
	List<UserProfile> findAll();
}
