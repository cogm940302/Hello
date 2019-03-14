package com.esc.services;

import com.esc.object.Product;

public interface ProductService {

	public Product getProductById(int id);
	public boolean delProductById(int id);
}
