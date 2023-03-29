package net.bodz.bas.codegen;

import java.io.IOException;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.BCharOut;

public abstract class AbstractJavaCodegen<model_t>
        implements
            IJavaCodegen<model_t> {

    protected abstract String getPackageName();

    public String build(model_t model) {
        BCharOut buf = new BCharOut();
        String packageName = getPackageName();
        JavaSourceWriter out = new JavaSourceWriter(packageName, buf.indented());
        try {
            generateJavaSource(out, model);
        } catch (IOException e) {
            throw new UnexpectedException("Error write to buffer: " + e.getMessage(), e);
        }
        return buf.toString();
    }

    protected abstract model_t getModel();

    @Override
    public String toString() {
        return build(getModel());
    }

}
