package com.demo.mygis.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mygis.domain.Pais;
import com.demo.mygis.repository.PaisRepository;


@RestController
@RequestMapping(path="/v1/paises")
public class PaisController {
	@Autowired
	private PaisRepository repository;

	
	@GetMapping
	public List<Pais> getAll() {
		return repository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody Pais pais){ 
		repository.save(pais);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pais> update(@PathVariable("id") String id, @RequestBody Pais pais) {
		return repository.findById(id)
				.map(record -> {
					record.setNombre(pais.getNombre()); 
					record.setContinente(pais.getContinente()); 
					record.setSuperficieMaritima(pais.getSuperficieMaritima());
					record.setSuperficieTerrestre(pais.getSuperficieTerrestre());
					
					Pais updated = repository.save(record); 
					
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
