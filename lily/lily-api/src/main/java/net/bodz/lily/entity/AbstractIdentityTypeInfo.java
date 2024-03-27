package net.bodz.lily.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.provider.bean.BeanTypeProvider;

public abstract class AbstractIdentityTypeInfo
        implements
            IIdentityTypeInfo {

    final Class<? extends IIdentity> idClass;

    Map<String, IProperty> _properties = null;

    public AbstractIdentityTypeInfo(Class<? extends IIdentity> idClass) {
        this.idClass = idClass;
    }

    @Override
    public Class<? extends IIdentity> getIdentityClass() {
        return idClass;
    }

    public Map<String, IProperty> getProperties() {
        if (_properties == null) {
            synchronized (this) {
                if (_properties == null) {
                    _properties = new LinkedHashMap<String, IProperty>();
                    loadProps();
                }
            }
        }
        return _properties;
    }

    void loadProps() {
        IType type = BeanTypeProvider.getInstance().getType(idClass);
        String[] names = getPropertyNames();
        for (String name : names) {
            IProperty property = type.getProperty(name);
            if (property == null)
                throw new IllegalUsageException("invalid property name: " + name);
            _properties.put(name, property);
        }
    }

    @Override
    public IProperty getProperty(String name) {
        return getProperties().get(name);
    }

}
