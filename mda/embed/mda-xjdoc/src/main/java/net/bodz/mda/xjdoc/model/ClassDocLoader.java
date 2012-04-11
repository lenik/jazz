package net.bodz.mda.xjdoc.model;

import net.bodz.bas.text.flatf.FlatfLoader;
import net.bodz.mda.xjdoc.util.TypeNameContext;

public class ClassDocLoader
        extends FlatfLoader {

    final TypeNameContext typeNameContext;

    public ClassDocLoader(Class<?> contextClass) {
        this(contextClass.getPackage().getName());
    }

    public ClassDocLoader(String contextPackageName) {
        if (contextPackageName == null)
            throw new NullPointerException("contextPackageName");
        this.typeNameContext = new TypeNameContext(contextPackageName);
    }

    public TypeNameContext getTypeNameContext() {
        return typeNameContext;
    }

    @Override
    protected void processInstruction(String piCommand, String piParams) {
        if ("import".equals(piCommand)) {
            String fqcn = piParams.trim();
            typeNameContext.importType(fqcn);
        } else {
            super.processInstruction(piCommand, piParams);
        }
    }

}
