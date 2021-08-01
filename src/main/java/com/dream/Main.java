package com.dream;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        ApplicationContext context = Application.run("com.dream", new HashMap<>(Map.of(Policeman.class, PolicemanImpl.class)));
//        CoronaDesinfector coronaDesinfector = context.getObject(CoronaDesinfector.class);
//        coronaDesinfector.start(new Room());
        ApplicationContext context = Application.run("com.dream", new HashMap<>(Map.of(String.class, String.class)));
        Vehicle vehicle = context.getObject(Vehicle.class);
        vehicle.move();
        vehicle.goMove();
    }
}
