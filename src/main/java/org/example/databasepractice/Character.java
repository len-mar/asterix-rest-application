package org.example.databasepractice;

import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.Id;

import java.time.Instant;

public record Character(@Id @Primary String id, String name, int age, String occupation, Instant creationTime) {
}
