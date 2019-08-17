package com.demo.mygis.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mygis.domain.Planta;
import com.demo.mygis.repository.PlantaRepository;

@RestController
@RequestMapping(path="/v1/plantas")
public class PlantaController {
	@Autowired
	private PlantaRepository repository;
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Planta> findById(@PathVariable String id) {
		return repository.findById(id)
		          .map(record ->
		ResponseEntity.ok().body(record)) .orElse(ResponseEntity.notFound().build());
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Planta> findByName(@RequestParam (value = "nombre") String nombre, Model model) {
		return repository.findByNombreComunLike(nombre);

	}
	
	@GetMapping
	public List<Planta> getAll() {
		return repository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody Planta planta){ 
		repository.save(planta);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Planta> update(@PathVariable("id") String id, @RequestBody Planta planta) {
		return repository.findById(id)
				.map(record -> {
					record.setNombreComun(planta.getNombreComun()); 
					record.setNombreCientifico(planta.getNombreCientifico());
					record.setEnPeligroExtincion(planta.isEnPeligroExtincion());
					
					Planta updated = repository.save(record); 
					
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
