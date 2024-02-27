package com.nkart.shoppingcart.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nkart.shoppingcart.dao.CategoryDAO;
import com.nkart.shoppingcart.domain.Category;

@Transactional
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	private static Logger log = LoggerFactory.getLogger(CategoryDAOImpl.class);

	@Autowired

	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public CategoryDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() {
		// select * from category--SQL Query --mention the table name

		// from Category --HQL --mention domain class name not table name

		// convert the hibernate query into db specific language
		log.debug("Ending of category list in IMPL");
		return getSession().createQuery("from Category").list();

	}

	public boolean createCategory(Category category) {
		try {
			log.debug("Strating of category save in IMPL");
			getSession().save(category);
			log.debug("Ending of category save in IMPL");
			return true;
		}

		catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	public boolean updateCategory(Category category) {
		try {
			log.debug("Starting of category update in IMPL");
			getSession().update(category);
			log.debug("Ending of category update in IMPL");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	

	public boolean deleteCategory(Category category) {

		try {
			log.debug("Starting of category delete in IMPL");
			getSession().delete(category);
			log.debug("Ending of category delete in IMPL");
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteCategoryByName(String name) {

		try {
			getSession().delete(getCategoryByName(name));
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}

	}

	public boolean deleteCategoryById(int id) {

		try {
			getSession().delete(getCategoryById(id));
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	// this method will return category based on category id./ if the category
	// does not exist it will return null
	public Category getCategoryById(int id) {
		// select * from category where id=...
		// this syntax works because id is primary key or
		// return (Category)
		// sessioFactory.getCurrentSession.get(Category.class,id);

		return (Category) getSession().createQuery("from Category where id = '" + id + "'").uniqueResult();
	}

	public Category getCategoryByName(String name) {
		// unique result will only work for primary key and unique values
		// select * from Category where name=...

		return (Category) getSession().createQuery("from Category where name= :parameter0").setParameter(":parameter0", name).list().get(0);

	}
}


