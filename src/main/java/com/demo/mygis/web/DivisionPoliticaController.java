package com.demo.mygis.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mygis.domain.DivisionPolitica;
import com.demo.mygis.domain.Pais;
import com.demo.mygis.repository.DivisionPoliticaRepository;
import com.demo.mygis.repository.PaisRepository;

@RestController
@RequestMapping(path="/v1/divisiones_politicas")
public class DivisionPoliticaController {
	@Autowired
	private DivisionPoliticaRepository repository;
	
	@Autowired
	private PaisRepository paisRepository;
	
	@GetMapping
	public List<DivisionPolitica> getAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<DivisionPolitica> findById(@PathVariable String id) {
		return repository.findById(id)
		          .map(record ->
		ResponseEntity.ok().body(record)) .orElse(ResponseEntity.notFound().build());
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<DivisionPolitica> findByPais(@RequestParam (value = "paisId") String paisId, Model model) {
		return repository.findByPaisId(paisId);

	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public List<DivisionPolitica> findByName(@RequestParam (value = "nombre") String nombre, Model model) {
		return repository.findByNombreLike(nombre);

	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody DivisionPolitica division){ 
		Optional<Pais> pais = paisRepository.findById(division.getPaisId());
		
		if (pais.isPresent()) {
			repository.save(division);
			
			pais.map(record -> {
				List<DivisionPolitica> divisiones = record.getDivisiones();
				
				if (divisiones != null) {
					divisiones.add(division);
					
					record.setDivisiones(divisiones);
	
				} else {
					List<DivisionPolitica> divisiones2 = new ArrayList<DivisionPolitica>();
					
					divisiones2.add(division);
					record.setDivisiones(divisiones2);
				}
				
				paisRepository.save(record);
				
				return record;
			});
			
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
				
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}

}
