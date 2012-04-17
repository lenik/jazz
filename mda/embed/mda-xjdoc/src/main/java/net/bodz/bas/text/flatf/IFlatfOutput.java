package net.bodz.bas.text.flatf;

import java.io.IOException;

import javax.free.ICharOut;

import net.bodz.bas.i18n.dstr.DomainString;

public interface IFlatfOutput {

    String getIndent();

    /**
     * Get the underlying writer.
     * 
     * @return Non-<code>null</code> writer.
     * @throws UnsupportedOperationException
     *             If there is no writer.
     */
    ICharOut getCharOut();

    void pi(String command, String data)
            throws IOException;

    void sectionBegin(String sectionName)
            throws IOException;

    void sectionEnd()
            throws IOException;

    void attribute(String name, String string)
            throws IOException;

    void attribute(String name, DomainString text)
            throws IOException;

}