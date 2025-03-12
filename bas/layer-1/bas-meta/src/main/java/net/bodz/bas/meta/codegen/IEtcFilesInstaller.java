package net.bodz.bas.meta.codegen;

import java.io.IOException;

public interface IEtcFilesInstaller {

    void install(Class<?> clazz, IEtcFilesEditor editor)
            throws IOException;

    // void remove(Class<?> clazz obj, IEtcFilesEditor editor);

}
