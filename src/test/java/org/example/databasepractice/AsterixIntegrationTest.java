package org.example.databasepractice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class AsterixIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CharacterRepo characterRepo;

    @DirtiesContext
    @Test
    void get() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/asterix/characters"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("[]"));

    }

    @DirtiesContext
    @Test
    void deleteById() throws Exception {
        String id = "1";
        Character character = new Character( "1", "Mayo", 13, "Job1", Instant.now());
        characterRepo.save(character);


        mvc.perform(MockMvcRequestBuilders.delete(String.format("/asterix/characters/%s", id)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(
                        """
                                { "id":  "1",
                                "name" : "Mayo",
                                "age" :  13,
                                "occupation":  "Job1"}
                                """
                ));
        assertThrows(NoSuchElementException.class, () -> characterRepo.findById(id).orElseThrow(NoSuchElementException::new));
    }

    // todo: update
    // todo: create
    }
