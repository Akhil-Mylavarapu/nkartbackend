package com.nkart.shoppingcart.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.nkart.shoppingcart.dao.SupplierDAO;
import com.nkart.shoppingcart.domain.Supplier;


@Repository("supplierDAO")
@Transactional
public class SupplierDAOImpl implements SupplierDAO
{
	private static Logger log = LoggerFactory.getLogger(SupplierDAOImpl.class);
	private SessionFactory sessionFactory;
	public SupplierDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Supplier> getAllSuppliers() 
	{
		log.debug("starting of supplier session in impl");
	return sessionFactory.getCurrentSession().createQuery("from Supplier").list();	
	}

	public boolean createSupplier(Supplier supplier) 
	{
		try {
			log.debug("starting of supplier create in impl");
			sessionFactory.getCurrentSession().save(supplier);
			log.debug("ending of supplier create in impl");

			return true;
			}
		catch (Exception e)
		{
		e.printStackTrace();
		return false;
		}
	}

	public boolean deleteSupplier(Supplier supplier) 
	{
				
		try{
			log.debug("starting of supplier delete in impl");
			sessionFactory.getCurrentSession().delete(supplier);
			log.debug("ending of supplier delete in impl");

			return true;
			}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateSupplier(Supplier supplier) {
		try {
			log.debug("starting of supplier update in impl");
			sessionFactory.getCurrentSession().update(supplier);
			log.debug("ending of supplier update in impl");

			return true;
			}
		catch (Exception e)
			{
			e.printStackTrace();
			return false;
			}
	}

	public Supplier getSupplierById(int id) 
	{
	return (Supplier) sessionFactory.getCurrentSession().createQuery("from Supplier where id='"+id+"'").uniqueResult();	
	}

	public Supplier getSupplierByName(int name) 
	{
		
	return	(Supplier) sessionFactory.getCurrentSession().createQuery("from Supplier where name='"+name+"'").list().get(0);
			
	}

}
