package net.bodz.bios;

import java.util.Collection;

public interface PortClass {

    boolean isDataTypeSupported(Class<?> dataType);

    Collection<Class<?>> getSupportedDataTypes();

}
