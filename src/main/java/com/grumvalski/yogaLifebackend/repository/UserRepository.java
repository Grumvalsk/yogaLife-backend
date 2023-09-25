package com.grumvalski.yogaLifebackend.repository;

import com.grumvalski.yogaLifebackend.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
