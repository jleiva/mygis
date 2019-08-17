package com.demo.mygis.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.demo.mygis.domain.Pais;
import com.demo.mygis.domain.RegionBiologica;
import com.demo.mygis.repository.AnimalRepository;
import com.demo.mygis.repository.RegionBiologicaRepository;

@RestController
@RequestMapping(path="/v1/animales")
public class AnimalController {
	@Autowired
	private AnimalRepository repository;
	
	@Autowired
	private RegionBiologicaRepository regionRepository;
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Animal> findById(@PathVariable String id) {
		return repository.findById(id)
		          .map(record ->
		ResponseEntity.ok().body(record)) .orElse(ResponseEntity.notFound().build());
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Animal> findByName(@RequestParam (value = "nombre") String nombre, Model model) {
		return repository.findByNombreComunLike(nombre);

	}
	
	@GetMapping
	public List<Animal> getAll() {
		return repository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody Animal animal) {
		Optional<RegionBiologica> region = regionRepository.findById(animal.getRegionId());
		
		if (region.isPresent()) {
			repository.save(animal);
			
			region.map(record -> {
				List<Animal> animales = record.getAnimales();
				
				if (animales != null) {
					animales.add(animal);
					
					record.setAnimales(animales);
	
				} else {
					List<Animal> animales2 = new ArrayList<Animal>();
					
					animales2.add(animal);
					record.setAnimales(animales2);
				}
				
				regionRepository.save(record);
				
				return record;
			});
			
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
				
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

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
