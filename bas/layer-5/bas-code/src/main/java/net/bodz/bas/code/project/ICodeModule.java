package net.bodz.bas.code.project;

import java.io.IOException;
import java.nio.file.Path;

public interface ICodeModule {

    void prepareFiles(Path projectDir)
            throws IOException;

}
