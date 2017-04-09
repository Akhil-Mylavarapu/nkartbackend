package com.nkart.shoppingcart.dao.impl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nkart.shoppingcart.dao.CartDAO;
import com.nkart.shoppingcart.domain.Cart;





@Repository
public class CartDAOImpl implements CartDAO {
	

private static Logger log = LoggerFactory.getLogger(CartDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public CartDAOImpl(SessionFactory sessionFactory) {
		log.debug("Starting of cart session");
		this.sessionFactory=sessionFactory;
		log.debug("ending of cart session");
	}
	
	@Transactional
	public boolean saveorupdate(Cart cart) {
		
		try {
			log.debug("Starting of cart save in IMPL");
			sessionFactory.getCurrentSession().saveOrUpdate (cart);
			log.debug("Ending of cart save in IMPL");
			return true;
		} catch (Exception e) {
			log.error("");
			e.printStackTrace();
			log.debug("Ending of cart save in IMPL");
			return false;
		}
		
	}
	@Transactional
	public boolean update(Cart cart) {
		try {
			log.debug("Starting of cart update in IMPL");
			sessionFactory.getCurrentSession().update(cart);
			log.debug("Ending of cart update in IMPL");
			return true;
		} catch (Exception e) {
			log.warn("cart update exception in impl");
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean delete(Cart cart) {
		try {
			log.debug("Starting of delete cart in impl");
			sessionFactory.getCurrentSession().delete(cart);
			log.debug("Ending of delete in cart impl ");
			return true;
		} catch (Exception e) {
			log.warn("exception in cart delete");
			e.printStackTrace();
			return false;
		}

	}
@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Cart> list() {
	log.debug("cart list started");
		List<Cart> listCart = (List<Cart>)sessionFactory.getCurrentSession()
						.createCriteria(Cart.class)
						.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
log.debug("cart list ended");
				return listCart;
			}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public List<Cart> get(int userid) {
		log.debug("user cart list started");
		String hql = "from"+" Cart"+" where userid="+userid+"and status='C'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Cart> list= (List<Cart>)query.list();
		log.debug("user cart list ended");
		return list;
	 
	}
	@Transactional
	@SuppressWarnings({ "unchecked" })
	public Cart getproduct(int productid,int userid) {
		log.debug("getproduct in cartimpl");
		String hql = "from"+" Cart"+" where Status='C'and userid="+userid+" and productid="+productid;
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Cart>listproduct=query.list();

		if(listproduct.isEmpty())
		{
			
			return null;
		}
		else
		{
			System.out.println("product");
			return listproduct.get(0);
		}
	}
	
	@Transactional
	public void pay(int userid) {
		log.debug("Starting of cart pay impl");		
		String hql="update Cart set status='P' where userid="+userid;	
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		log.debug(hql);
		log.debug("Ending of cart pay in IMPL");
	}
	

	@SuppressWarnings("deprecation")
	@Transactional
	public long CartPrice(int userId) {
		log.debug("Starting of cart cartprice impl");
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Cart.class);
		c.add(Restrictions.eq("userid", userId));
		c.add(Restrictions.eq("status","C"));
		c.setProjection(Projections.sum("price"));
		Long l= (Long)c.uniqueResult();
		log.debug("Ending of cart price in IMPL");
		return l;
	}
	@SuppressWarnings("deprecation")
	@Transactional
	public long cartsize(int userId) {
		log.debug("Starting of cart cartsize impl");
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Cart.class);
		c.add(Restrictions.eq("userid", userId));
		c.add(Restrictions.eq("status","C"));
		c.setProjection(Projections.count("userid"));
		Long count=(Long) c.uniqueResult();
		log.debug("Ending of size update in IMPL");
		return count;
	}
	
	@Transactional
	public Cart getitem(int cartId) {
		log.debug("Starting of cart getitem impl");
		String hql = "from"+" Cart"+" where id="+cartId;
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Cart> list = (List<Cart>) query.list();
		if (list!= null && !list.isEmpty()) {
			log.debug("Ending of cart getitem in IMPL");
			return list.get(0);
		}
		return null;
	}

}
