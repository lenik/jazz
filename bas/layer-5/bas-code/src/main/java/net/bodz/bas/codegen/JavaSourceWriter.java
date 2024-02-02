package net.bodz.bas.codegen;

import net.bodz.bas.io.DecoratedTreeOut;
import net.bodz.bas.io.ITreeOut;

public class JavaSourceWriter
        extends DecoratedTreeOut {

    private static final long serialVersionUID = 1L;

    public final JavaImports im;

    public JavaSourceWriter(String packageName, ITreeOut _orig) {
        super(_orig);
        im = new JavaImports(packageName);
    }

}
