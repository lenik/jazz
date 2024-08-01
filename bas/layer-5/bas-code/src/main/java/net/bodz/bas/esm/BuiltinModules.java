package net.bodz.bas.esm;

public class BuiltinModules
        implements
            IEsmModuleProvider {

    public static final int PRIORITY_LOCAL = 1000;

    public static final EsmModule local = EsmModule.local(PRIORITY_LOCAL);

    static final EsmModule[] MODULES = { local };

    @Override
    public EsmModule[] getModules() {
        return MODULES;
    }

}
