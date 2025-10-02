package haru;

import haru.application.Application;

public class Haru {

    private final Application application;

    Haru() {
        application = new Application();
    }

    public static void main(String[] args) {
        Application haru = new Application();
        haru.run();
    }
}
