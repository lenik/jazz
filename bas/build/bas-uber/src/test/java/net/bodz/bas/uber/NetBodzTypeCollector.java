package net.bodz.bas.uber;

import net.bodz.bas.c.loader.scan.TypeCollector;

public class NetBodzTypeCollector<T>
        extends TypeCollector<T> {

    public NetBodzTypeCollector() {
        super();
    }

    public NetBodzTypeCollector(Class<?> baseClass, Boolean global) {
        super(baseClass, global);
    }

    {
        includePackageToScan("net.bodz");
        includePackageToScan("user");
    }

}
