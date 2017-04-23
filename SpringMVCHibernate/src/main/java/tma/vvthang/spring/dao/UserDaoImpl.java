package tma.vvthang.spring.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tma.vvthang.spring.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao{

	public User findById(int id) {
		User user = getByKey(id);
		if(user != null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findBySSOID(String ssoId) {
		System.out.print("SSOID: " + ssoId);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", ssoId));
		User user = (User) crit.uniqueResult();
		if(user != null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public void save(User user) {
		persist(user);
		
	}

	public void deleteBySSOID(String ssoId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eqOrIsNull("ssoId", ssoId));
		User user = (User)crit.uniqueResult();
		delete(user);
		
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUser() {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("firstName"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<User> listUsers = (List<User>)crit.list();
		return listUsers;
	}

}
