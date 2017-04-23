package tma.vvthang.spring.dao;

import java.util.List;

import tma.vvthang.spring.model.User;

public interface UserDao {
	
	User findById(int id);
	User findBySSOID(String ssoId);
	void save(User user);
	void deleteBySSOID(String ssoId);
	List<User> findAllUser();
}
