package com.demo.mygis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.mygis.domain.DivisionPolitica;

public interface DivisionPoliticaRepository extends MongoRepository<DivisionPolitica, String> {
	List<DivisionPolitica> findAllByPaisId(String paisId);
}
