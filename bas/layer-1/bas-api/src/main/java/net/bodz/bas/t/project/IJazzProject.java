package net.bodz.bas.t.project;

import java.util.List;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IJazzProject {

    List<IJazzModule> getModules();

}
