package net.bodz.bas.codegen;

import net.bodz.bas.io.DecoratedTreeOut;
import net.bodz.bas.io.ITreeOut;

public class JavaSourceWriter
        extends DecoratedTreeOut
        implements
            IImportNaming {

    private static final long serialVersionUID = 1L;

    public final JavaImports im;

    public JavaSourceWriter(String packageName, ITreeOut _orig) {
        super(_orig);
        im = new JavaImports(packageName);
    }

    @Override
    public String importName(Class<?> clazz) {
        return im.name(clazz);
    }

    @Override
    public String importName(QualifiedName name) {
        return im.name(name);
    }

    @Override
    public String importName(String name) {
        return im.name(name);
    }

}
