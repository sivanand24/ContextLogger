package net.codex.journalApp.repository;

import net.codex.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
    Optional<User> findByEmail(String email);
    void deleteByUserName(String username);
}

