package net.bodz.bas.cli;

import java.util.Map.Entry;
import java.util.TreeMap;

import net.bodz.bas.potato.traits.AbstractType;
import net.bodz.bas.potato.traits.IConstructorMap;
import net.bodz.bas.potato.traits.IEventMap;
import net.bodz.bas.potato.traits.IMethodMap;
import net.bodz.bas.potato.traits.IPropertyMap;

public class CLIType<T extends BasicCLI>
        extends AbstractType {

    private final T dynamicImpl;

    @SuppressWarnings("unchecked")
    public CLIType(Class<T> origClass, Object dynamicImpl, boolean inheritsConvert)
            throws ReflectiveOperationException {
        super(origClass, inheritsConvert ? Scripts.convertClass(origClass) : null);
        this.dynamicImpl = (T) dynamicImpl;
        try {
            load();
        } catch (CLIException e) {
            throw new ReflectiveOperationException(e.getMessage(), e);
        }
    }

    public CLIType(Class<T> origClass, Object dynamicImpl)
            throws ReflectiveOperationException {
        this(origClass, dynamicImpl, false);
    }

    @SuppressWarnings("unchecked")
    public CLIType(Object dynamicImpl)
            throws ReflectiveOperationException {
        this((Class<T>) dynamicImpl.getClass(), dynamicImpl);
    }

    protected void load()
            throws CLIException {
        ClassOptions<BasicCLI> copts = dynamicImpl.getOptions();
        TreeMap<String, _Option<?>> map = copts.getOptions();
        for (Entry<String, _Option<?>> e : map.entrySet()) {
            // String cliKey = e.getKey();
            _Option<?> opt = e.getValue();
            String name = opt.getName();
            if (opt instanceof MethodOption)
                loadMethod(name, (MethodOption) opt);
            else
                loadField(name, opt);
        }
    }

    void loadField(String name, _Option<?> opt) {
        putField(name, opt);
    }

    void loadMethod(String name, MethodOption opt) {
        putMethod(name, opt);
    }

    @Override
    public IPropertyMap getPropertyMap() {
        return null;
    }

    @Override
    public IMethodMap getMethodMap() {
        return null;
    }

    @Override
    public IConstructorMap getConstructorMap() {
        return null;
    }

    @Override
    public IEventMap getEventMap() {
        return null;
    }

}
