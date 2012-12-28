package net.bodz.bas.potato.element;

public class MutableType
        extends AbstractType {

    private MutablePropertyMap propertyMap = new MutablePropertyMap();
    private MutableMethodMap methodMap = new MutableMethodMap();
    private MutableConstructorMap constructorMap = new MutableConstructorMap();
    private MutableEventMap eventMap = new MutableEventMap();

    public MutableType(Class<?> clazz) {
        super(clazz, clazz.getName());
    }

    @Override
    public MutablePropertyMap getPropertyMap() {
        return propertyMap;
    }

    @Override
    public MutableMethodMap getMethodMap() {
        return methodMap;
    }

    @Override
    public MutableConstructorMap getConstructorMap() {
        return constructorMap;
    }

    @Override
    public MutableEventMap getEventMap() {
        return eventMap;
    }

}
