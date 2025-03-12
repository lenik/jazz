package net.bodz.bas.meta.codegen;

import java.io.IOException;

public interface IEtcFilesEditor {

    void clear(String path)
            throws IOException;

    void addLine(String path, String s)
            throws IOException;

}
