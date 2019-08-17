package com.demo.mygis.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.mygis.domain.Planta;

public interface PlantaRepository extends MongoRepository<Planta, String> {

	List<Planta> findByNombreComunLike(String nombre);

}
