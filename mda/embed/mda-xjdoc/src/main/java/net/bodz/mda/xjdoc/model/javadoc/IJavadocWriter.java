package net.bodz.mda.xjdoc.model.javadoc;

import java.io.IOException;

public interface IJavadocWriter {

    void writeTag(String tagName, String string)
            throws IOException;

}
