package net.bodz.xml.extb;

import org.xml.sax.InputSource;

public interface ExtbParser {

    String getPublicId();

    String getSystemId();

    String getNsPrefix(String uri);

    String getNsURI(String nsPrefix);

    /**
     * @param in
     *            SAX XML input source
     * @param eh
     *            if <code>null</code>, report by <code>reportError</code>
     */
    void parse(InputSource in, ExceptionHandler eh);

    void reportError(ExceptionType type, Throwable e);

    void reportError(ExceptionType type, String mesg);

}
