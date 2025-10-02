package haru.util;

/**
 * A simple counter used to track the size of a collection,
 * such as an {@link haru.application.Application}.
 *
 * This class can be incremented externally by multiple files
 * to reflect changes in the number of elements in the collection.
 */
public class Counter {
    /** The current count of elements in the collection. */
    public int value;

    /**
     * Constructs a Counter with the specified initial value.
     *
     * @param initial The starting count of elements in the collection.
     */
    public Counter(int initial) {
        this.value = initial;
    }

}
