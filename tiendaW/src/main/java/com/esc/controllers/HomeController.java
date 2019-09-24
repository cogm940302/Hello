package com.esc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
/**http://websystique.com/springmvc/spring-4-mvc-rest-service-example-using-restcontroller/*/

	@GetMapping("/prueba")
	public String prueba() {
		return "Si se pudo";
	}

}
