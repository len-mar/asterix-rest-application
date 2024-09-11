package org.example.databasepractice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AsterixService {
    private final CharacterRepo characterRepo;

    public List<Character> findAll(){
        return characterRepo.findAll();
    }

    // todo: how is this usually done? exceptions?
    // todo: fix null return
    public Character findById(String id){
        Optional<Character> result = characterRepo.findById(id);
        return result.orElse(null);
    }

    public List<Character>  deleteById(String id){
        characterRepo.deleteById(id);
        return characterRepo.findAll();
    }

    public Character createCharacter(CharacterDTO characterDTO){
        return characterRepo.save(new Character(IdService.generateId(), characterDTO.name(), characterDTO.age(), characterDTO.occupation()));
    }

    // todo: is there another way other than creating a whole new character object? with timestamps, this would get tricky
    // the solution is creation and modification timestamps
    public Character updateCharacter(CharacterDTO characterDTO){
        Character oldCharacter = characterRepo.findByName(characterDTO.name());
        return characterRepo.save(
                new Character(oldCharacter.id(), characterDTO.name(), characterDTO.age(), characterDTO.occupation()));
    }


}
