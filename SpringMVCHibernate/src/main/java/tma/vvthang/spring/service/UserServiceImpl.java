package tma.vvthang.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tma.vvthang.spring.dao.UserDao;
import tma.vvthang.spring.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	public User findById(int id) {
		return userDao.findById(id);
	}

	public User findBySSOID(String ssoId) {
		return userDao.findBySSOID(ssoId);
	}

	public void saveUser(User user) {
		userDao.save(user);
	}

	public void updateUser(User user) {
		User entity = userDao.findById(user.getId());
		if(entity != null){
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setPassword(user.getPassword());
			entity.setSsoId(user.getSsoId());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	public void deleteUserBySSOID(String ssoId) {
		userDao.deleteBySSOID(ssoId);
		
	}

	public List<User> findAllUser() {
		return userDao.findAllUser();
	}

	public boolean isUserSSOUnique(Integer id, String ssoId) {
		User user = findBySSOID(ssoId);
		return (user == null || (id != null && user.getId() == id));
	}

}
