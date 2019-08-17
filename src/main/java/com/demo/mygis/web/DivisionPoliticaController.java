package com.demo.mygis.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ResponseEntity<DivisionPolitica> findByPaisid(@RequestParam (value = "pais") String paisId, Model model) {
//		return repository.findAllByPaisId(paisId).map(record ->
//		ResponseEntity.ok().body(record)) .orElse(ResponseEntity.notFound().build());
//
//	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody DivisionPolitica division){ 
		Optional<Pais> pais = paisRepository.findById(division.getPaisId());
		
		if (pais != null) {
			repository.save(division);
			
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
				
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}

}
