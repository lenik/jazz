package net.bodz.uni.shelj.codegen.java;

import java.io.IOException;

public abstract class JavaCodeBuilder {

    public abstract void build(JavaCodeWriter out)
            throws IOException;

}
