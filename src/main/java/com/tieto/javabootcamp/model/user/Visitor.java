package com.tieto.javabootcamp.model.user;

import java.util.Set;

public class Visitor extends User {

    public static final String GUEST = "guest";

    public Visitor() {
        super(GUEST, "", Set.of());
    }

    @Override
    public String getName() {
        return GUEST;
    }

}
