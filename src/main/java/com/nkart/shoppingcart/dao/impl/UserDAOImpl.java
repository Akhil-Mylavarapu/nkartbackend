package com.nkart.shoppingcart.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nkart.shoppingcart.dao.UserDAO;
import com.nkart.shoppingcart.domain.User;

@Transactional
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	private static Logger log=LoggerFactory.getLogger(UserDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return sessionFactory.getCurrentSession().createQuery("from User").list();

	}
	@Transactional
	public boolean createUser(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}
	@Transactional
	public boolean updateUser(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Transactional
	public boolean deleteUser(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Transactional
	public User getUserById(int id) {
		return (User) sessionFactory.getCurrentSession().createQuery("from User where id='" + id + "'").uniqueResult();
	}
	@Transactional
	public User getUserByName(String name) {
		return (User) sessionFactory.getCurrentSession().createQuery("from User where name='" + name + "'").list().get(0);
	}
	@Transactional
	public User validate(String id, String password)
	{
		// select * from User where id='' and password=''
		String hql="from User where id='"+id+"' and password ='"+password+"'";
		if(sessionFactory.getCurrentSession().createQuery(hql).uniqueResult()==null)
			{
			return null;
			}
		else
			return (User) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

}
