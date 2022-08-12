package net.bodz.lily.codegen.doc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public class IndexInfo
        extends AbstractTypeInfo {

    Map<String, IMethod> methods = new HashMap<>();

    public IndexInfo(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public Map<String, IProperty> getPropertyMap() {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, IMethod> getMethodMap() {
        return methods;
    }

    @Override
    protected void parse(IType declaredType, ModuleIndexer indexer) {
        for (IMethod method : declaredType.getMethods()) {
            methods.put(method.getName(), method);
            // TODO Annotate methods with @BindPath
        }
    }

}
