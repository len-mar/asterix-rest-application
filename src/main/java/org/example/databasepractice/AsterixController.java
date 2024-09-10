package org.example.databasepractice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class AsterixController {
private final CharacterRepo characterRepo;

    @GetMapping("/asterix/characters")
    List<Character> findAll(){
        return characterRepo.findAll();
    }

    @DeleteMapping("/asterix/characters/{id}")
    List<Character>  deleteById(@PathVariable String id){
        characterRepo.deleteById(id);
        return characterRepo.findAll();
    }


}
