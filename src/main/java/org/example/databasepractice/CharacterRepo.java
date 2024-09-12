package org.example.databasepractice;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CharacterRepo extends MongoRepository<Character, String> {
    Character findByName(String name);

    List<Character> findByAgeLessThanEqual(Integer age);
}
