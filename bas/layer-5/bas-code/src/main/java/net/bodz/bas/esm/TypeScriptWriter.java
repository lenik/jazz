package net.bodz.bas.esm;

import net.bodz.bas.io.DecoratedTreeOut;
import net.bodz.bas.io.ITreeOut;

public class TypeScriptWriter
        extends DecoratedTreeOut {

    private static final long serialVersionUID = 1L;

    public final EsmImports im;

    public TypeScriptWriter(EsmSource source, ITreeOut _orig) {
        super(_orig);
        im = new EsmImports(source);
    }

}
