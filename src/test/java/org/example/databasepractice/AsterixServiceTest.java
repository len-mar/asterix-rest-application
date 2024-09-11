package org.example.databasepractice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AsterixServiceTest {

private final CharacterRepo characterRepo = mock(CharacterRepo.class);
AsterixService asterixService = new AsterixService(characterRepo);

    @Test
    void findAll() {
        // given
        // when
        // then
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateCharacter() {
    }
}