package com.nkart.shoppingcart.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nkart.shoppingcart.dao.ProductDAO;
import com.nkart.shoppingcart.domain.Product;
@SuppressWarnings("deprecation")
@Transactional
@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO 
{
	private static Logger log= LoggerFactory.getLogger(ProductDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public ProductDAOImpl(SessionFactory sessionFactory)
	{
		log.debug("starting of product session in IMPL");
		this.sessionFactory=sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() 
	{
		log.debug("starting of product list in IMPL");
	return	sessionFactory.getCurrentSession().createQuery("from Product").list();
	}

	public boolean createProduct(Product product) 
	{
		try {
			log.debug("starting of product create in IMPL");
			sessionFactory.getCurrentSession().save(product);
			log.debug("Ending of product create in IMPL");
			return true;
			}
		catch (Exception e)
		{
		
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateProduct(Product product) {
		try {
			log.debug("starting of product update in impl");
			sessionFactory.getCurrentSession().update(product);
			log.debug("ending of product update in impl");
			return true;
			}
		catch (Exception e)
		{
		
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteProduct(Product product) {
		try {
			log.debug("starting of product delete in impl");
			sessionFactory.getCurrentSession().delete(product);
			log.debug("ending of product delete in impl");
			return true;
			}
		catch (Exception e)
		{
		
			e.printStackTrace();
			return false;
		}
	}

	public Product getProductById(int id) {
		return (Product) sessionFactory.getCurrentSession().createQuery("from Product where id='"+id+"'").uniqueResult();
	}

	public Product getProductByName(int name) {
		return (Product) sessionFactory.getCurrentSession().createQuery("from Product where name='"+name+"'").list().get(0);
	}
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Product> getproduct(int id) {
		String hql="from Product where id= "+id;
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Product> listProduct = (List<Product>) query.list();
		return listProduct;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public List<Product> navproduct(int id) {
		String hql = "from Product where category_id= "+id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Product> catproducts = (List<Product>) query.list();
		return catproducts;
	}

}
