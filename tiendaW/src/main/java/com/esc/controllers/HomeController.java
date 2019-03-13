package com.esc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/prueba")
	public String prueba() {
		return "Si se pudo";
	}
}
