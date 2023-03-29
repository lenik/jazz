package net.bodz.bas.shell.util.fieldsCg;

import java.io.IOException;

import net.bodz.bas.codegen.JavaSourceWriter;

public abstract class JavaSourceBuilder {

    public abstract void build(JavaSourceWriter out)
            throws IOException;

}
