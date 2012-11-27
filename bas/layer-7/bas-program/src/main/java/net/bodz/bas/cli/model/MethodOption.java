package net.bodz.bas.cli.model;

import java.lang.reflect.Method;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.potato.model.IProperty;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.model1.ArtifactDoc;

public class MethodOption
        extends TransientOption {

    private final Method method;

    public MethodOption(Method method, MethodDoc methodDoc) {
        super(method.getName(), method.getReturnType(), method, //
                methodDoc.as(ArtifactDoc.class));
        this.method = method;
    }

    @Override
    public IProperty property() {
        throw new NotImplementedException();
    }

}
