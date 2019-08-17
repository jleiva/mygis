package com.demo.mygis.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.mygis.domain.Animal;

public interface AnimalRepository extends MongoRepository<Animal, String> {

	Optional<Animal> findByNombreComunLike(String nombre);

}
