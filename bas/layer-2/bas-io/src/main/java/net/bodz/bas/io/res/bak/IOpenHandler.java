package net.bodz.bas.io.res.bak;

import java.io.IOException;
import java.nio.file.OpenOption;

public interface IOpenHandler {

    void beforeOpenInput(OpenOption... options)
            throws IOException;

    void beforeOpenOutput(OpenOption... options)
            throws IOException;

    void afterOpenInput(AutoCloseable in)
            throws IOException;

    void afterOpenOutput(AutoCloseable out)
            throws IOException;

}
