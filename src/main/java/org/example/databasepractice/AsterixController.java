package org.example.databasepractice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;
// TODO: * implement a endpoint in the AsterixController that returns a Character based on its id.
//  implement a endpoint in the AsterixController that deletes a Character based on its id.
//  implement a endpoint in the AsterixController that updates a Character based on its id.

@RequiredArgsConstructor
@RestController
public class AsterixController {
    private final AsterixService asterixService;

    @GetMapping("/asterix/characters")
    public List<Character> findAll(){
        return asterixService.findAll();
    }

    @DeleteMapping("/asterix/characters/{id}")
    public Character  deleteById(@PathVariable String id){
        return asterixService.deleteById(id);
    }

    @PostMapping("/asterix/characters/create")
    public Character createCharacter(@RequestBody CharacterDTO characterDTO){
        return asterixService.createCharacter(characterDTO);
    }

    @PutMapping("/asterix/characters/update")
    public Character updateCharacter(@RequestBody CharacterDTO characterDTO){
        return asterixService.updateCharacter(characterDTO);
    }


}
