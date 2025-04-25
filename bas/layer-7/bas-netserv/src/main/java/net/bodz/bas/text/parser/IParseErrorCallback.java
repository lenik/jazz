package net.bodz.bas.text.parser;

public interface IParseErrorCallback {

    /**
     * @return true drop the error text parsed, continue to parse the remaining text. false stop at the error text.
     */
    boolean parseError(IParseContext context, Throwable e);

}
