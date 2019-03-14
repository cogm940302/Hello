package com.esc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esc.object.Product;
import com.esc.services.ProductService;

@RestController
@RequestMapping("/producto")
public class ProductController {

	@Autowired
	ProductService pService;
	
	@GetMapping("/{id}")
	public Product getProducto(@PathVariable("id")int id) {
		Product p = pService.getProductById(id);
		return p;
	}
	
	@DeleteMapping("/{id}")
	public boolean delProduct(@PathVariable("id")int id) {
		return pService.delProductById(id);
	}
	
	@PostMapping("/")
	public boolean addProduct(@RequestBody Product product) {
		return pService.setProduct(product);
	}
	
	@PutMapping("/")
	public Product updateProduct(@RequestBody Product product) {
		return pService.updateProduct(product);
	}
}
