package com.cd2.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cd2.domain.Model;
import com.cd2.service.Servico;

@Controller
@RequestMapping(value = "/sigabem")
@CrossOrigin("*")
public class ControllerCD2 {
	
	@Autowired
	private Servico servico;
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Model>> findAll() {
		List<Model> model = servico.findAll();
		return ResponseEntity.ok().body(model);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Model> findById(@PathVariable Integer id) {
		Model model = servico.findById(id);
		return ResponseEntity.ok().body(model);
	}

	@PostMapping(value = "/")
	public ResponseEntity<Model> create(@Valid @RequestBody Model model) {
		model = servico.create(model);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(model.getId()).toUri();
		return ResponseEntity.created(uri).body(model);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		servico.delete(id);
		return ResponseEntity.noContent().build();
	}
}
