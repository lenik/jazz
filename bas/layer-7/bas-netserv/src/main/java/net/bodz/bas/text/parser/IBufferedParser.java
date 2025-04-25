package net.bodz.bas.text.parser;

/**
 * Interface for a buffered parser that can process data in chunks.
 */
public interface IBufferedParser<T> {

    /**
     * Parses the buffer, removing any parsed text from the buffer and leaving unparsed or error-parsed text in it.
     *
     * @param la The lookahead object to be used for parsing.
     */
    default void parse(IParsedValueCallback<T> callback) {
        parse(callback, null);
    }

    default void parse(IParseCallback<T> callback) {
        parse(callback, callback);
    }

    void parse(IParsedValueCallback<T> callback, IParseErrorCallback errorCallback);

}
