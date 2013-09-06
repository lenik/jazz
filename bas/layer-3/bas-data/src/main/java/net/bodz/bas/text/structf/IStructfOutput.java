package net.bodz.bas.text.structf;

import java.io.IOException;

public interface IStructfOutput {

    void beginElement(String name, String... args)
            throws IOException;

    void endElement()
            throws IOException;

    void attribute(String name, String data)
            throws IOException;

}
