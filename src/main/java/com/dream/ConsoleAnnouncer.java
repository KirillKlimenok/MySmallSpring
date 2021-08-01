package com.dream;

public class ConsoleAnnouncer implements Announcer {
    @InjectByType
    private Recommendator recommendator;

    @Override
    public void announce(String massage) {
        System.out.println(massage);
        recommendator.recommend();
    }
}
