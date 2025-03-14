package net.bodz.bas.meta.codegen;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.meta.decl.NotNull;

@FunctionalInterface
public interface INamedTextBuffers {

    @NotNull
    List<String> loadText(String name)
            throws IOException;

}
