package net.bodz.bas.t.project;

import java.util.Set;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IJazzModule
        extends
            IPriority,
            IClassHashConstants {

    int PRIORITY_MODULE = 0;
    int PRIORITY_PROJECT = 100;
    int PRIORITY_WEBAPP = 1000;

    String getName();

    IJazzProject getProject();

    Set<String> getManagedClassNames();

}
