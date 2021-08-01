package com.dream;

//@Deprecated
public class Vehicle {

    @InjectByType
    private String string;

    @NonReturnedNull
    public String move() {
        System.out.println("Returned null");
        return null;
    }

    @NonReturnedNull
    public String goMove() {
        System.out.println("returned " + string);
        return string;
    }

}
