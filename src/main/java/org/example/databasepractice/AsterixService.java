package org.example.databasepractice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AsterixService {
    private final CharacterRepo characterRepo;

    public List<Character> findAll(){
        return characterRepo.findAll();
    }

    public Character findById(String id){
        Optional<Character> result = characterRepo.findById(id);
        return result.orElseThrow(NoSuchElementException::new);
    }

    public Character deleteById(String id){
        Character deletedCharacter = characterRepo.findById(id).get();
        characterRepo.deleteById(id);
        return deletedCharacter;
    }

    public Character createCharacter(CharacterDTO characterDTO){
        return characterRepo.save(new Character(IdService.generateId(), characterDTO.name(), characterDTO.age(), characterDTO.occupation(), Instant.now()));
    }

    public Character updateCharacter(CharacterDTO characterDTO){
        Character oldCharacter = characterRepo.findByName(characterDTO.name());
        return characterRepo.save(
                new Character(oldCharacter.id(), characterDTO.name(), characterDTO.age(), characterDTO.occupation(), oldCharacter.creationTime()));
    }


}
