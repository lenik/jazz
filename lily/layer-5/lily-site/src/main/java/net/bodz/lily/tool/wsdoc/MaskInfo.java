package net.bodz.lily.tool.wsdoc;

import java.util.Collections;
import java.util.Map;

import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public class MaskInfo
        extends AbstractTypeInfo {

    public MaskInfo(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public Map<String, IMethod> getMethodMap() {
        return Collections.emptyMap();
    }

    @Override
    protected void parse(IType declaredType, ModuleIndexer indexer) {
        for (IProperty property : declaredType.getProperties()) {
            String name = property.getName();
            if (properties.containsKey(name))
                // overrided by derived class.
                continue;
            addProperty(property);
        }
    }

}
