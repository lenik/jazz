package net.bodz.bas.esm;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IEsmModuleProvider {

    public static final int PRIORITY_BASE = 100;

    EsmModule[] getModules();

}
