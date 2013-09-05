package net.bodz.bas.text.flatf;

import java.io.IOException;

import net.bodz.bas.i18n.dom.iString;

public interface IFlatfOutput {

    /**
     * Get the indentation pattern. This pattern can be used to generate beautiful multi-line
     * attribute values.
     */
    String getHskip();

    void pi(String command, String data)
            throws IOException;

    void beginSection(String sectionName)
            throws IOException;

    void endSection()
            throws IOException;

    void attribute(String name, String string)
            throws IOException;

    void attribute(String name, iString text)
            throws IOException;

}