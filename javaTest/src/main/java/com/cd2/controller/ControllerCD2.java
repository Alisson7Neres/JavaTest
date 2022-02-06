package com.cd2.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
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
import com.google.gson.Gson;

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
	public ResponseEntity<Model> create(@Valid @RequestBody Model model) throws ParseException {

		if(model.getCepDestino() != null) {
			cepDestino(model);
		} if (model.getCepOrigem() != null) {
			cepOrigem(model);
		}
		String dddOrigem = model.getDddOrigem();
		String dddDestino = model.getDddDestino();
		String ufOrigem = model.getUfOrigem(); 
		String ufDestino = model.getUfDestino();
		

		
		if(dddOrigem.equals(dddDestino) && ufOrigem.equals(ufDestino)) {
			Double desconto = model.getVlTotalFrete();
			desconto *= 1.0;
			desconto *= 0.75;
			model.getDataPrevistaEntrega();
			model.setVlTotalFrete(desconto);
			
			dataPrevistaEstado(model);
			
			
			if(dddOrigem.equals(dddDestino)) {
				Double desconto2 = model.getVlTotalFrete();
				desconto2 *= 0.5;
				model.setVlTotalFrete(desconto2);
				
				dataPrevistaDdd(model);
			}
		}
		
		if(!ufOrigem.equals(ufDestino)) {
			Double desconto = model.getVlTotalFrete();
			desconto *= 1.0;
			
			model.setVlTotalFrete(desconto);
			
			dataPrevista(model);
		}
		
		Date d = new Date();
		DateFormat.getInstance().format(new Date());

		model.getDataConsulta();
		model.setDataConsulta(d);
		
		pesoTaxa(model);
		model = servico.create(model);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(model.getId()).toUri();
		return ResponseEntity.created(uri).body(model);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		servico.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	public void cepDestino(Model model) {
		
		try {
			URL url = new URL("https://viacep.com.br/ws/"+model.getCepDestino()+"/json/");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			StringBuilder jsonCEP = new StringBuilder();
			
			String cepDestino = "";
			
			while((cepDestino = br.readLine()) != null) {
				jsonCEP.append(cepDestino);
			}
			
			System.out.println(jsonCEP.toString());
			
			Model aux = new Gson().fromJson(jsonCEP.toString(), Model.class);
	
			model.setCep(aux.getCep());
			model.setDdd(aux.getDdd());
			model.setUf(aux.getUf());
			model.setDddDestino(aux.getDdd());
			model.setUfDestino(aux.getUf());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cepOrigem(Model model) {
		
		try {
			URL url = new URL("https://viacep.com.br/ws/"+model.getCepOrigem()+"/json/");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			StringBuilder jsonCEP = new StringBuilder();
			
			String cepOrigem = "";
			
			while((cepOrigem = br.readLine()) != null) {
				jsonCEP.append(cepOrigem);
			}
			
			System.out.println(jsonCEP.toString());
			
			Model aux = new Gson().fromJson(jsonCEP.toString(), Model.class);
	
			model.setCep(aux.getCep());
			model.setDdd(aux.getDdd());
			model.setUf(aux.getUf());
			model.setDddOrigem(aux.getDdd());
			model.setUfOrigem(aux.getUf());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pesoTaxa(@Valid @RequestBody Model model) {
		Double peso = model.getPeso();
		peso *= 1.0;
		model.setVlTotalFrete(peso);
		Double frete  = model.getVlTotalFrete();
		Math.floor(frete);
	}
	
	public void dataPrevistaDdd(@Valid @RequestBody Model model) {
		
		Date d = new Date();
		DateFormat.getInstance().format(new Date());
		
		model.getDataConsulta();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DATE, 1);
		d = cal.getTime();
		
		model.setDataConsulta(d);
		
		model.setDataPrevistaEntrega(model.getDataConsulta());
	}
	
	public void dataPrevistaEstado(@Valid @RequestBody Model model) {
		
		Date d = new Date();
		DateFormat.getInstance().format(new Date());

		model.getDataConsulta();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DATE, 3);
		d = cal.getTime();
		
		model.setDataConsulta(d);
		
		model.setDataPrevistaEntrega(model.getDataConsulta());
	}
	
	public void dataPrevista(@Valid @RequestBody Model model) {
		
		Date d = new Date();
		DateFormat.getInstance().format(new Date());
		
		model.getDataConsulta();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DATE, 10);
		d = cal.getTime();
		
		model.setDataConsulta(d);
		
		model.setDataPrevistaEntrega(model.getDataConsulta());
	}
}

