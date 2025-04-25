package net.bodz.bas.text.parser;

@FunctionalInterface
public interface IParsedValueCallback<T> {

    void parsedValue(IParseContext context, T value);

}
