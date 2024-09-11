package org.example.databasepractice;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.example.databasepractice.IdService.generateId;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsterixServiceTest {

private final CharacterRepo mockCharacterRepo = mock(CharacterRepo.class);
AsterixService asterixService = new AsterixService(mockCharacterRepo);

    @Test
    void findAll_returnsEmptyList_whenCalled() {
        // given
        List<Character> expected = List.of();
        when(mockCharacterRepo.findAll()).thenReturn(List.of());
        // when
        List<Character> actual = asterixService.findAll();
        verify(mockCharacterRepo).findAll();
        // then
        assertEquals(expected, actual);
    }

    @Test
    void findById_returnsExpected_whenIdIsFound() {
        // given
        String id = "1";
        Character expected = new Character(id, "Asterix", 50, "Alphawolf", Instant.now());
        when(mockCharacterRepo.findById(id)).thenReturn(Optional.of(expected));
        // when
        Character actual = asterixService.findById(id);
        verify(mockCharacterRepo).findById(id);
        // then
        assertEquals(expected,actual);
    }

    @Test
    void findById_returnsExpected_whenIdIsNotFound() {
        // given
        String id = "0";
        when(mockCharacterRepo.findById(id)).thenReturn(Optional.empty());
        // when
        assertThrows(NoSuchElementException.class, () -> asterixService.findById(id));
    }

    // todo: actually test the delete
    @Test
    void deleteById() {
        // given
        String id = "1";
        Character expected = new Character(id, "Asterix", 50, "Alphawolf", Instant.now());
        when(mockCharacterRepo.findAll()).thenReturn(List.of(expected));
        // when
        Character actual = asterixService.deleteById(id);
        assertEquals(expected,actual);
    }

    @Test
    void updateCharacter() {
        // given
        String id = "1";
        // fake "old" character we want to update
        Character oldCharacter = new Character(id, "Asterix", 50, "Alphawolf", Instant.now());
        // fake "new" character input
        CharacterDTO newCharacterDTO = new CharacterDTO("Asterix", 5, "None");
        // expected updated character (age & occupation different)
        Character expected = new Character(id,"Asterix", 5, "None", oldCharacter.creationTime());
        when(mockCharacterRepo.findByName(oldCharacter.name())).thenReturn(oldCharacter);
        when(mockCharacterRepo.save(expected)).thenReturn(expected);
        // when
        Character actual = asterixService.updateCharacter(newCharacterDTO);
        verify(mockCharacterRepo).save(expected);
        verify(mockCharacterRepo).findByName(expected.name());

        // then
        assertEquals(expected,actual);
    }

    @Test
    void createCharacter_createsCharacter(){
        // given
        String mockId = "123";
        try (MockedStatic<IdService> mockedStatic = Mockito.mockStatic(IdService.class)) {
            // given
            mockedStatic.when(IdService::generateId).thenReturn(mockId);
            CharacterDTO newCharacterDTO = new CharacterDTO("Betarix", 20, "Beta");
            Character expected = new Character(generateId(),"Betarix", 20, "Beta", Instant.now());
            when(mockCharacterRepo.save(any())).thenReturn(expected);

            // when
            Character actual = asterixService.createCharacter(newCharacterDTO);
            verify(mockCharacterRepo).save(any());

            // then
            assertEquals(expected.name(), actual.name());
            assertEquals(expected.id(), actual.id());
            assertEquals(expected.occupation(), actual.occupation());

            assertTrue(actual.creationTime().isAfter(Instant.parse("2020-01-01T09:00:00.00Z")));
        }




    }
}