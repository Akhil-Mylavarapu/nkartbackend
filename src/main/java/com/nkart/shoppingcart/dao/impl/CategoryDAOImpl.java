package com.nkart.shoppingcart.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nkart.shoppingcart.dao.CategoryDAO;
import com.nkart.shoppingcart.domain.Category;

@Transactional
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired

	private SessionFactory sessionFactory;
	
	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public CategoryDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() {
		// select * from category--SQL Query --mention the table name

		// from Category --HQL --mention domain class name not table name

		// convert the hibernate query into db specific language

		return getSession().createQuery("from Category").list();

	}

	public boolean createCategory(Category category) {
		try {
			
			getSession().save(category);
			return true;
		}

		catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}
	public boolean updateCategory(Category category) {
		try {
//			if (category.getId() != null)
//				category1 = getCategoryById(category.getId());
//			else if (category.getName() != null)
//				category1 = getCategoryByName(category.getName());
			
			System.err.println("Getting the Category: "+category);
			getSession().update(category);

			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/*public boolean updateCategory(Category category) {

		try {
			getSession().update(category);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}*/

	public boolean deleteCategory(Category category) {
		
		try {
			/*if (category.getId() != null)
				category1 = getCategoryById(category.getId());
			else if (category.getName() != null)
				category1 = getCategoryByName(category.getName());*/
			getSession().delete(category);

			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteCategoryByName(String name) {

		try {
			getSession().delete(getCategoryByName(name));
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

		return (Category) getSession().createQuery("from Category where id = '" + id + "'")
				.uniqueResult();
	}

	public Category getCategoryByName(String name) {
		// unique result will only work for primary key and unique values
		// select * from Category where name=...

		return (Category) getSession().createQuery("from Category where name= '" + name + "'")
				.list().get(0);

	}
}

	/*@Autowired
	SessionFactory sessionFactory;
	public static final Logger log = LoggerFactory.getLogger(CategoryDAOImpl.class);
    public CategoryDAOImpl(SessionFactory  sessionFactory) {
    this.sessionFactory=sessionFactory;
}

	@Transactional
	public boolean save(Category category) {
		try {
			log.info("category start:");
			sessionFactory.getCurrentSession().saveOrUpdate(category);
			log.info("category saved:");
			return true;
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	
	@Transactional
	public boolean delete(int id) {
		Category CategoryToDelete = new Category();
		CategoryToDelete.setId(id);
		 sessionFactory.getCurrentSession().delete(CategoryToDelete);
		 return true;
	}
	
		
		@Transactional
		public Category get(int id) {
			String hql = "from"+" Category"+" where id=" + id;
		
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			@SuppressWarnings({ "unchecked", "deprecation" })
			List<Category> listCategory = (List<Category>) query.list();
			
			if (listCategory != null && !listCategory.isEmpty()) {
				return listCategory.get(0);
			}
			
			return null;
		}

	
	@Transactional
	public List<Category> list() {
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Category> listCategory = (List<Category>) sessionFactory.getCurrentSession()
				.createCriteria(Category.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listCategory;
	}

	
}*/
