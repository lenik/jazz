package net.bodz.bas.esm.extern;

import net.bodz.bas.esm.EsmModule;
import net.bodz.bas.esm.IEsmModuleProvider;

public class ExternModules
        implements
            IEsmModuleProvider {

    public static final int PRIORITY_DATATABLES = PRIORITY_BASE + 1;

    public static final Esm_jQuery jQuery = new Esm_jQuery(PRIORITY_BASE);
    public static final Esm_vue vue = new Esm_vue(PRIORITY_BASE);
    public static final Esm_moment momentTz = new Esm_moment(PRIORITY_BASE);
    public static final Esm_datatables datatables = new Esm_datatables(PRIORITY_DATATABLES);

    static final EsmModule[] MODULES = { jQuery, vue, momentTz, datatables };

    @Override
    public EsmModule[] getModules() {
        return MODULES;
    }

}
