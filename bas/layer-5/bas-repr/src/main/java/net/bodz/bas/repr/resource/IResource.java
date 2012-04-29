package net.bodz.bas.repr.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public interface IResource {

    String getName();

    String getPath();

    InputStream openBinary()
            throws IOException;

    Reader openText()
            throws IOException;

}
