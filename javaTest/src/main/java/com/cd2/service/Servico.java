package com.cd2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd2.domain.Model;
import com.cd2.exceptions.ObjectNotFoundException;
import com.cd2.repository.Repositorio;

@Service
public class Servico {
	
	@Autowired
	private Repositorio repository;
	
	public List<Model> findAll() {
		return repository.findAll();
	}
	
	public Model findById(Integer id) {
		Optional<Model> model = repository.findById(id);
		return model.orElseThrow((() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", tipo " + Model.class.getName())));
	}
	
	public Model create(Model model) {
		model.setId(null);
		return repository.save(model);
	}
	
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}

}
