package net.bodz.bas.t.project;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IJazzModule
        extends IClassHashConstants {

    String getName();

    IJazzProject getProject();

}
