package com.esc.services;

import com.esc.object.Product;

public interface ProductService {

	public Product getProductById(int id);
	public boolean delProductById(int id);
	public boolean setProduct(Product product);
	public Product updateProduct(Product product);
	
}
