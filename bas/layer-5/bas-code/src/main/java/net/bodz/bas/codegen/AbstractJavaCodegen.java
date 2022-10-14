package net.bodz.bas.codegen;

import net.bodz.bas.io.BCharOut;

public abstract class AbstractJavaCodegen<model_t>
        implements
            IJavaCodegen<model_t> {

    protected abstract String getPackageName();

    public String build(model_t model) {
        BCharOut buf = new BCharOut();
        String packageName = getPackageName();
        JavaSourceWriter out = new JavaSourceWriter(packageName, buf.indented());
        generateJavaSource(out, model);
        return buf.toString();
    }

    protected abstract model_t getModel();

    @Override
    public String toString() {
        return build(getModel());
    }

}
