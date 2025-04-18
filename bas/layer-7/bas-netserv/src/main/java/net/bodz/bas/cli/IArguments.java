package net.bodz.bas.cli;

import net.bodz.bas.meta.decl.NotNull;

public interface IArguments {

    @NotNull
    String[] toArray();

    /**
     * Returns a string representation of all arguments, separated by spaces.
     *
     * @return a string of arguments
     */
    @NotNull
    String toString(String delim);

    void setArray(String[] args);

    void setArray(String[] args, int off, int len);

    /**
     * Returns an argument at a specified index (1-based).
     *
     * @param index the 1-based index of the argument to retrieve
     * @return the argument at the specified index, not null
     * @throws IndexOutOfBoundsException
     */
    @NotNull
    String getArgument(int index);

    /**
     * Returns an argument at a specified index (1-based), with a fallback value if the index is out of bounds.
     *
     * @param index the 1-based index of the argument to retrieve
     * @param fallback the fallback value to return if the index is out of bounds
     * @return the argument at the specified index or the fallback value
     */
    String getArgument(int index, String fallback);

    /**
     * Adds an argument to the command.
     *
     * @param arg the argument to add, not null
     */
    void addArgument(@NotNull String arg);

    /**
     * Returns the number of arguments associated with the command.
     *
     * @return the number of arguments
     */
    int getArgumentCount();

    /**
     * Checks if there are no arguments associated with the command.
     *
     * @return true if there are no arguments, false otherwise
     */
    default boolean isNoArgument() {
        return getArgumentCount() == 0;
    }

    /**
     * Checks if there are arguments associated with the command.
     *
     * @return true if there are arguments, false otherwise
     */
    default boolean isAnyArgument() {
        return getArgumentCount() != 0;
    }

    /**
     * Checks if an argument is present at a specified index (1-based).
     *
     * @param index the 1-based index of the argument to check
     * @return true if the argument is present at the specified index, false otherwise
     */
    default boolean isArgumentPresent(int index) {
        if (index < 1 || index > getArgumentCount())
            return false;
        return true;
    }

    default boolean isArgumentNotPresent(int index) {
        return !isArgumentPresent(index);
    }

    Command makeCommand();

}
