package com.tieto.javabootcamp.model.user;

public class Visitor extends User {

    public static final String GUEST = "guest";

    public Visitor() {
        super(GUEST);
    }

    @Override
    public String getName() {
        return GUEST;
    }

}
