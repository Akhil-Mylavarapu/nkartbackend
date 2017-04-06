package com.nkart.shoppingcart.dao;

import java.util.List;

import com.nkart.shoppingcart.domain.Product;

public interface ProductDAO 
{
	public List<Product> getAllProducts();
	
	public boolean createProduct(Product product);
	
	public boolean updateProduct(Product product);
	
	public boolean deleteProduct(Product product);
	
	public Product getProductById(int id);
	
	public Product getProductByName(int name);

	public List<Product> navproduct(int id);
	
	
	public List<Product> getproduct(int id);


	
	
	
}
