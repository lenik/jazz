package net.bodz.bas.shell.util.fieldsCg;

import java.io.IOException;

import net.bodz.bas.codegen.JavaSourceWriter;

public abstract class JavaSourceBuilder {

    public abstract void build(JavaSourceWriter out)
            throws IOException;

    protected JavaSourceBuilder printf(JavaSourceWriter out, String format, Object... args) {
        String s = String.format(format, args);
        out.print(s);
        return this;
    }

    protected JavaSourceBuilder print(JavaSourceWriter out, String s) {
        out.print(s);
        return this;
    }

    protected JavaSourceBuilder println(JavaSourceWriter out, String s) {
        out.println(s);
        return this;
    }

}
