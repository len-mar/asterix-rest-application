package org.example.databasepractice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CharacterRepo extends MongoRepository<Character, String> {
    Character findByName(String name);
}
