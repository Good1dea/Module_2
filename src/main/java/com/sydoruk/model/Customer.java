package com.sydoruk.model;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Setter
@Getter
public class Customer {

    private String email;
    private int age;
    @Getter
    private final String id;

    public Customer() {
        this.id = UUID.randomUUID().toString();
    }
}