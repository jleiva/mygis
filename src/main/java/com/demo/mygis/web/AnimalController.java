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

import com.demo.mygis.domain.Animal;
import com.demo.mygis.repository.AnimalRepository;

@RestController
@RequestMapping(path="/v1/animales")
public class AnimalController {
	@Autowired
	private AnimalRepository repository;
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Animal> findById(@PathVariable String id) {
		return repository.findById(id)
		          .map(record ->
		ResponseEntity.ok().body(record)) .orElse(ResponseEntity.notFound().build());
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Animal> findByName(@RequestParam (value = "nombre") String nombre, Model model) {
		return repository.findByNombreComunLike(nombre).map(record ->
		ResponseEntity.ok().body(record)) .orElse(ResponseEntity.notFound().build());

	}
	
	@GetMapping
	public List<Animal> getAll() {
		return repository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody Animal animal){ 
		repository.save(animal);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Animal> update(@PathVariable("id") String id, @RequestBody Animal animal) {
		return repository.findById(id)
				.map(record -> {
					record.setNombreComun(animal.getNombreComun()); 
					record.setNombreCientifico(animal.getNombreCientifico());
					record.setEnPeligroExtincion(animal.isEnPeligroExtincion());
					record.setPoblacion(animal.getPoblacion());
					
					Animal updated = repository.save(record); 
					
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
