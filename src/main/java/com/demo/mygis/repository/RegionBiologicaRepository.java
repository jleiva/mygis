package com.demo.mygis.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.demo.mygis.domain.RegionBiologica;

public interface RegionBiologicaRepository extends MongoRepository<RegionBiologica, String> {
	@Query("{ 'paisId' : ?0 }")
	List<RegionBiologica> findByPaisId(String paisId);

	List<RegionBiologica> findByNombreLike(String nombre);

}
