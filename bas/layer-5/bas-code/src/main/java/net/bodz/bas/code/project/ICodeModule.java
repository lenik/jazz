package net.bodz.bas.code.project;

import java.io.File;
import java.io.IOException;

public interface ICodeModule {

    void prepareFiles(File projectDir)
            throws IOException;

}
