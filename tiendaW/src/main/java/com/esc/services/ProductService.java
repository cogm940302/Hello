package com.esc.services;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.esc.object.Product;

public interface ProductService extends MongoRepository<Product,String>{

	public Product getProductById(int id);
	public boolean delProductById(int id);
	public boolean setProduct(Product product);
	public Product updateProduct(Product product);
	public String algo(String nombre);
	
}
