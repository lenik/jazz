package net.bodz.bas.flow;

import java.util.Collection;

public interface IPortClass {

    boolean isDataTypeSupported(Class<?> dataType);

    Collection<Class<?>> getSupportedDataTypes();

}
