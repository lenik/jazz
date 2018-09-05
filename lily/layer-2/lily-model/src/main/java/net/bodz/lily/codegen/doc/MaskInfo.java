package net.bodz.lily.codegen.doc;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public class MaskInfo
        extends AbstractTypeInfo<MaskInfo> {

    Map<String, IProperty> properties = new HashMap<>();

    public MaskInfo(Class<?> clazz) {
        super(clazz);
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
