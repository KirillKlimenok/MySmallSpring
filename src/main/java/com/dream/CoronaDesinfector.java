package com.dream;

public class CoronaDesinfector {
    @InjectByType
    private Announcer announcer;
    @InjectByType
    private Policeman policeman;

    public void start(Room room) {
        announcer.announce("Начинаем дезинфекциию, все вон!");
        policeman.makePeopleLeaveRoom();
        desinfect(room);
        announcer.announce("Можете зайти обратно!");
    }

    private void desinfect(Room room) {
        System.out.println("Дезинфекция!!!");
    }
}
