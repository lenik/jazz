package net.bodz.bas.text.flatf;

import java.io.IOException;

import net.bodz.bas.i18n.dstr.DomainString;

public interface IFlatfOutput {

    String getIndent();

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