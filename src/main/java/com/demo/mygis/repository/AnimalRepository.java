package com.demo.mygis.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.mygis.domain.Animal;

public interface AnimalRepository extends MongoRepository<Animal, String> {

	List<Animal> findByNombreComunLike(String nombre);

}
