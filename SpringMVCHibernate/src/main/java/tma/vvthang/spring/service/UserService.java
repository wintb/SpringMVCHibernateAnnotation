package tma.vvthang.spring.service;

import java.util.List;

import tma.vvthang.spring.model.User;

public interface UserService {
	User findById(int id);
	User findBySSOID(String ssoId);
	void saveUser(User user);
	void updateUser(User user);
	void deleteUserBySSOID(String ssoId);
	List<User> findAllUser();
	boolean isUserSSOUnique(Integer id, String ssoId);
	
}
