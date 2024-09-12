package org.example.databasepractice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AsterixService {
    private final CharacterRepo characterRepo;

    public Character createCharacter(CharacterDTO characterDTO){
        return characterRepo.save(new Character(IdService.randomId(), characterDTO.name(), characterDTO.age(), characterDTO.occupation(), Instant.now()));
    }

    public List<Character> findAll(){
        return characterRepo.findAll();
    }

    public List<Character> findByAgeLessThanEqual(Integer age){
        return characterRepo.findByAgeLessThanEqual(age);
    }

    public Character findById(String id){
        Optional<Character> result = characterRepo.findById(id);
        return result.orElseThrow(NoSuchElementException::new);
    }

    public Character updateCharacter(String id, CharacterDTO characterDTO){
        Character oldCharacter = characterRepo.findById(id).orElseThrow(NoSuchElementException::new);
        return characterRepo.save(
                new Character(oldCharacter.id(), characterDTO.name(), characterDTO.age(), characterDTO.occupation(), oldCharacter.creationTime()));
    }

    public Character deleteById(String id){
        Character deletedCharacter = characterRepo.findById(id).orElseThrow(NoSuchElementException::new);
        characterRepo.deleteById(id);
        return deletedCharacter;
    }
}
