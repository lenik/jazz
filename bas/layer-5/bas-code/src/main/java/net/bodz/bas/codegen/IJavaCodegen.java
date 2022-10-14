package net.bodz.bas.codegen;

public interface IJavaCodegen<model_t> {

    void generateJavaSource(JavaSourceWriter out, model_t model);

}
