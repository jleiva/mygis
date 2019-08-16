package com.demo.mygis.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.demo.mygis.domain.Pais;

public interface PaisRepository extends MongoRepository<Pais, String> {

}
