package net.bodz.bas.codegen;

import java.io.IOException;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ICharOut;

public abstract class AbstractJavaCodegen<out_t, model_t>
        implements
            IJavaCodegen<out_t, model_t> {

    protected abstract String getPackageName();

    public String build(model_t model) {
        BCharOut buf = new BCharOut();
        out_t out = createOutput(buf);
        try {
            generateJavaSource(out, model);
        } catch (IOException e) {
            throw new UnexpectedException("Error write to buffer: " + e.getMessage(), e);
        }
        return buf.toString();
    }

    protected abstract out_t createOutput(ICharOut charOut);

    protected abstract model_t getModel();

    @Override
    public String toString() {
        return build(getModel());
    }

}
