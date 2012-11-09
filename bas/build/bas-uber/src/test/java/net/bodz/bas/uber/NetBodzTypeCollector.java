package net.bodz.bas.uber;

import net.bodz.bas.c.type.TypeCollector;

public class NetBodzTypeCollector<T>
        extends TypeCollector<T> {

    public NetBodzTypeCollector() {
        super();
    }

    public NetBodzTypeCollector(Class<?> baseClass, Boolean global) {
        super(baseClass, global);
    }

    {
        addPackageToScan("net.bodz");
        addPackageToScan("user");
    }

}
