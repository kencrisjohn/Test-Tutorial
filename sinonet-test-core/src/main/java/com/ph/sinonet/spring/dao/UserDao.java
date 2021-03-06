package com.ph.sinonet.spring.dao;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ph.sinonet.spring.model.entity.User;

@Repository
@Transactional
public class UserDao extends GenericDao<User>{

	
	public User getUserByName(String name){
		return findById(name);
	}

	
	public void saveUser(User user){
		save(user);
	}

	public void updateUser(User user){
		update(user);
	}
	
	public User getUserAuth(String name){
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("username"))
				.add(Projections.property("password")));
		criteria.add(Restrictions.eq("username", name));
		criteria.setResultTransformer(Transformers.aliasToBean(User.class));
		
		return (User) criteria.uniqueResult();
	}
	
	public User findUserWithLockMode(String name, LockMode lock){
		return findById(name, lock);
	}
	
}


