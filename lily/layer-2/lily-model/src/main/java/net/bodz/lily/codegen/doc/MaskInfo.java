package net.bodz.lily.codegen.doc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public class MaskInfo
        extends AbstractTypeInfo<MaskInfo> {

    Map<String, IProperty> properties = new HashMap<>();

    public MaskInfo(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public Map<String, IProperty> getPropertyMap() {
        return properties;
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
            properties.put(name, property);
        }
    }

}
