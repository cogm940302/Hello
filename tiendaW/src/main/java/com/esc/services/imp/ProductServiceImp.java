package com.esc.services.imp;

import org.springframework.stereotype.Service;

import com.esc.object.Product;
import com.esc.services.ProductService;

@Service("pService")
public class ProductServiceImp implements ProductService {

	@Override
	public Product getProductById(int id) {
		return new Product();
	}

	@Override
	public boolean delProductById(int id) {
		return false;
	}

}
