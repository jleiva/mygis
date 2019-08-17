package com.demo.mygis.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.demo.mygis.domain.DivisionPolitica;

public interface DivisionPoliticaRepository extends MongoRepository<DivisionPolitica, String> {
	@Query("{ 'paisId' : ?0 }")
	List<DivisionPolitica> findByPaisId(String paisId);

	List<DivisionPolitica> findByNombreLike(String nombre);

}
