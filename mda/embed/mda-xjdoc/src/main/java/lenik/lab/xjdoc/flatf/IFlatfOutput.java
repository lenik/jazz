package lenik.lab.xjdoc.flatf;

import java.io.IOException;

public interface IFlatfOutput {

    void sectionBegin(String sectionName)
            throws IOException;

    void sectionEnd()
            throws IOException;

    void attribute(String name, String text)
            throws IOException;

}