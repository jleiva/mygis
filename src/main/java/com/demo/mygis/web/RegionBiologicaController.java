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

import com.demo.mygis.domain.Pais;
import com.demo.mygis.domain.RegionBiologica;
import com.demo.mygis.repository.PaisRepository;
import com.demo.mygis.repository.RegionBiologicaRepository;

@RestController
@RequestMapping(path="/v1/regiones_biologicas")
public class RegionBiologicaController {
	@Autowired
	private RegionBiologicaRepository repository;
	
	@Autowired
	private PaisRepository paisRepository;
	
	@GetMapping
	public List<RegionBiologica> getAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<RegionBiologica> findById(@PathVariable String id) {
		return repository.findById(id)
		          .map(record ->
		ResponseEntity.ok().body(record)) .orElse(ResponseEntity.notFound().build());
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<RegionBiologica> findByPais(@RequestParam (value = "paisId") String paisId, Model model) {
		return repository.findByPaisId(paisId);

	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public List<RegionBiologica> findByName(@RequestParam (value = "nombre") String nombre, Model model) {
		return repository.findByNombreLike(nombre);

	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody RegionBiologica region){ 
		Optional<Pais> pais = paisRepository.findById(region.getPaisId());
		
		if (pais.isPresent()) {
			repository.save(region);
			
			pais.map(record -> {
				List<RegionBiologica> regiones = record.getRegiones();
				
				if (regiones != null) {
					regiones.add(region);
					
					record.setRegiones(regiones);
	
				} else {
					List<RegionBiologica> regiones2 = new ArrayList<RegionBiologica>();
					
					regiones2.add(region);
					record.setRegiones(regiones2);
				}
				
				paisRepository.save(record);
				
				return record;
			});
			
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
				
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}

}
