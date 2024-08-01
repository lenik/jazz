package net.bodz.bas.esm.skeljs;

import net.bodz.bas.esm.EsmModule;
import net.bodz.bas.esm.IEsmModuleProvider;

public class SkeljsModules
        implements
            IEsmModuleProvider {

    public static final int PRIORITY_SKELJS_CORE = 200;
    public static final int PRIORITY_SKELJS_DBA = 201;

    public static final Core core = new Core(PRIORITY_SKELJS_CORE);
    public static final Dba dba = new Dba(PRIORITY_SKELJS_DBA);

    static final EsmModule[] MODULES = { core, dba };

    @Override
    public EsmModule[] getModules() {
        return MODULES;
    }

}
