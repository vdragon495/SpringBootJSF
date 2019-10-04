package ru.technolab.demo;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {
	
	@GetMapping("/books")
	public String manageBooks(Model model) {
		return "books";
	}
	
	@GetMapping("/users")
	public String manageUsers(Model model) {
		return "users";
	}
}