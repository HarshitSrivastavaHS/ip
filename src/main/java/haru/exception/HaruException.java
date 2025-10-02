package haru.exception;

/**
 * Represents exceptions specific to the Haru application.
 *
 * This exception is thrown whenever an error occurs during
 * command parsing, validation, or other runtime operations
 * within the Haru chatbot.
 */
public class HaruException extends Exception {

    /**
     * Constructs a new {@code HaruException} with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public HaruException(String message) {
        super(message);
    }
}
