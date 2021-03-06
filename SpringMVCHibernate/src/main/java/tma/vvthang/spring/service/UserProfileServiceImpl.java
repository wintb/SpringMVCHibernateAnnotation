package tma.vvthang.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tma.vvthang.spring.dao.UserProfileDao;
import tma.vvthang.spring.model.UserProfile;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService{

	@Autowired
	UserProfileDao userProfileDao;
	
	public UserProfile findByType(String type) {
		return userProfileDao.findByType(type);
	}

	public UserProfile findById(int id) {
		return userProfileDao.findById(id);
	}

	public List<UserProfile> findAll() {
		return userProfileDao.findAll();
	}

}
