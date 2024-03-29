package net.bodz.lily.tool.wsdoc;

import java.util.Collections;
import java.util.Map;

import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public class CriteriaBuilderInfo
        extends AbstractTypeInfo {

    public CriteriaBuilderInfo(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public Map<String, IMethod> getMethodMap() {
        return Collections.emptyMap();
    }

    @Override
    protected void initType(IType declaredType, ModuleIndexer indexer) {
        for (IProperty property : declaredType.getProperties()) {
            String name = property.getName();
            if (properties.containsKey(name)) // already added.
                // Don't overrided existing property.
                continue;
            checkToAddProperty(property);
        }
    }

}
