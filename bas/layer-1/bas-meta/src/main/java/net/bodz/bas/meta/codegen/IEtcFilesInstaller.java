package net.bodz.bas.meta.codegen;

import java.io.IOException;

import net.bodz.bas.meta.decl.NotNull;

public interface IEtcFilesInstaller {

    void install(@NotNull Class<?> clazz, @NotNull INamedTextBuffers textBuffers)
            throws IOException;

    // void remove(Class<?> clazz obj, IEtcFilesEditor editor);

}
