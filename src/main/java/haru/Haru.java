package haru;

import haru.application.Application;

/**
 * Main class for the Haru application.
 * Initializes and runs the chatbot application.
 */
public class Haru {

    /** The main Application instance used by Haru. */
    private final Application application;

    /**
     * Constructs a new Haru instance and initializes the chatbot application.
     */
    Haru() {
        application = new Application();
    }

    /**
     * Entry point of the Haru Chatbot.
     * Creates an Application instance and starts it.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Application haru = new Application();
        haru.run();
    }
}
