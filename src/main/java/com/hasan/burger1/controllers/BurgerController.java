package com.hasan.burger1.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hasan.burger1.models.Burger;
import com.hasan.burger1.services.BurgerService;

@Controller
public class BurgerController {
	@Autowired
	BurgerService burgerService;
	
	@GetMapping("/")
	public String index(Model model, @ModelAttribute("burger") Burger burger) {
		List<Burger> burgers = burgerService.findAll();
		model.addAttribute("burgers", burgers);
		return "index.jsp";
	}
	@PostMapping("/")
	public String create(
			@Valid @ModelAttribute("burger") Burger burger,
			BindingResult result
			) {
		if(result.hasErrors()) {
			return "redirect:/";
		} else {
			burgerService.createBurger(burger);
			return "redirect:/";
		}
	}
}
