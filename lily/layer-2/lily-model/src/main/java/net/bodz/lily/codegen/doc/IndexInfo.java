package net.bodz.lily.codegen.doc;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.potato.element.IType;

public class IndexInfo
        extends AbstractTypeInfo<IndexInfo> {

    Map<String, IMethod> methods = new HashMap<>();

    public IndexInfo(Class<?> clazz) {
        super(clazz);
    }

    @Override
    protected void parse(IType declaredType) {
        for (IMethod method : declaredType.getMethods()) {
            methods.put(method.getName(), method);
            // TODO Annotate methods with @BindPath
        }
    }

}
