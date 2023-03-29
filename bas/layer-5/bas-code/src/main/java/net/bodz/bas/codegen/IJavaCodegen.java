package net.bodz.bas.codegen;

import java.io.IOException;

public interface IJavaCodegen<model_t> {

    void generateJavaSource(JavaSourceWriter out, model_t model)
            throws IOException;

}
