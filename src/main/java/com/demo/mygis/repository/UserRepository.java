package com.demo.mygis.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.demo.mygis.model.User;

public interface UserRepository extends MongoRepository<User, Long> { 
	Optional<User> findByUsername(String username);
}
