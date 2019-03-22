package com.esc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.esc.object.Product;
import com.esc.services.ProductService;

@RestController
public class HomeController {
/**http://websystique.com/springmvc/spring-4-mvc-rest-service-example-using-restcontroller/*/
	@Autowired
	ProductService pService;
	
	@GetMapping("/prueba")
	public String prueba() {
		return "Si se pudo";
	}
	
//	@GetMapping("/producto/{id}")
	public Product producto(@PathVariable("id") int id) {
		System.out.println("id : " + id);
		Product p = pService.getProductById(1);
		p.setDescripcion("algo");
		p.setNombre("nombre");
		p.setPrecio(2.0);
		return p;
	}
}
