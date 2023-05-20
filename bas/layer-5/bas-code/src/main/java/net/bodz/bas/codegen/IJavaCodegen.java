package net.bodz.bas.codegen;

import java.io.IOException;

public interface IJavaCodegen<out_t, model_t> {

    void generateJavaSource(out_t out, model_t model)
            throws IOException;

}
