package net.bodz.bas.flow;

import java.util.Collection;

public interface PortClass {

    boolean isDataTypeSupported(Class<?> dataType);

    Collection<Class<?>> getSupportedDataTypes();

}
