package net.bodz.bas.t.project;

import java.util.Set;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IJazzModule
        extends IClassHashConstants {

    String getName();

    IJazzProject getProject();

    Set<String> getManagedClassNames();

}
