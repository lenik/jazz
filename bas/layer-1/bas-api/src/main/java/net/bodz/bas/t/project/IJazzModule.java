package net.bodz.bas.t.project;

import java.util.Set;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IJazzModule
        extends
            IPriority,
            IClassHashConstants {

    String getName();

    IJazzProject getProject();

    Set<String> getManagedClassNames();

}
