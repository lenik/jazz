package net.bodz.uni.shelj.codegen.java;

import java.io.IOException;

import net.bodz.bas.codegen.JavaSourceWriter;

public abstract class JavaSourceBuilder {

    public abstract void build(JavaSourceWriter out)
            throws IOException;

}
