package com.hasan.burger1.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String create(Model model,
			@Valid @ModelAttribute("burger") Burger burger,
			BindingResult result
			) {
		if(result.hasErrors()) {
			List<Burger> burgers = burgerService.findAll();
			model.addAttribute("burgers", burgers);
			return "index.jsp";
		} else {
			burgerService.createBurger(burger);
			return "redirect:/";
		}
	}
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Burger burger = burgerService.findBurger(id);
		model.addAttribute("burger", burger);
		return "edit.jsp";
	}
	@RequestMapping(value="/burgers/{id}", method=RequestMethod.PUT)
	public String update(@Valid @ModelAttribute("burger") Burger burger, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		} else {
			burgerService.updateBurger(burger);
			return "redirect:/";
		}
	}
	@RequestMapping(value="/burgers/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Long id) {
		burgerService.deleteBurger(id);
		return "redirect:/";
	}
	
	
}
