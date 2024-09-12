package org.example.databasepractice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AsterixController {
    private final AsterixService asterixService;

    @PostMapping("/asterix/characters/create")
    public Character createCharacter(@RequestBody CharacterDTO characterDTO){
        return asterixService.createCharacter(characterDTO);
    }

    @GetMapping("/asterix/characters")
    public List<Character> findAll(@RequestParam(required = false) Integer age){
        if(age!=null){
            return asterixService.findByAgeLessThanEqual(age);
        }
        return asterixService.findAll();
    }

    @GetMapping("/asterix/characters/{id}")
    public Character  findById(@PathVariable String id){
        return asterixService.findById(id);
    }

    @PutMapping("/asterix/characters/update/{id}")
    public Character updateCharacter(@PathVariable String id, @RequestBody CharacterDTO characterDTO){
        return asterixService.updateCharacter(id, characterDTO);
    }

    @DeleteMapping("/asterix/characters/{id}")
    public Character  deleteById(@PathVariable String id){
        return asterixService.deleteById(id);
    }


}
