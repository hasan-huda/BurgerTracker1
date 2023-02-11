package com.hasan.burger1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hasan.burger1.models.Burger;
import com.hasan.burger1.repositories.BurgerRepository;

@Service
public class BurgerService {
	private final BurgerRepository burgerRepository;
	public BurgerService(BurgerRepository burgerRepository) {
		this.burgerRepository=burgerRepository;
	}
	public Burger createBurger(Burger b) {
		return burgerRepository.save(b);
	}
	public Burger findBurger(Long id) {
        Optional<Burger> optionalBurger = burgerRepository.findById(id);
        if(optionalBurger.isPresent()) {
            return optionalBurger.get();
        } else {
            return null;
        }
	}
	public List<Burger> findAll(){
		return burgerRepository.findAll();
	}
	public Burger updateBurger(Burger b) {
		return burgerRepository.save(b);
	}
	public void deleteBurger(Long id) {
		this.burgerRepository.deleteById(id);
	}
}
