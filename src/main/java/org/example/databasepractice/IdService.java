package org.example.databasepractice;

import java.util.UUID;

public class IdService {
    public static String randomId(){
        return UUID.randomUUID().toString();
    }
}
