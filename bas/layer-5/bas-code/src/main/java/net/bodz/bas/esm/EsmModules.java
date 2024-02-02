package net.bodz.bas.esm;

import net.bodz.bas.esm.base.Esm_datatables;
import net.bodz.bas.esm.base.Esm_jQuery;
import net.bodz.bas.esm.base.Esm_vue;
import net.bodz.bas.esm.skeljs.Core;
import net.bodz.bas.esm.skeljs.CoreVue;
import net.bodz.bas.esm.skeljs.Dba;
import net.bodz.bas.esm.skeljs.DbaVue;

public interface EsmModules {

    int PRIORITY_BASE = 10;
    int PRIORITY_DATATABLES = 11;

    int PRIORITY_SKELJS = 20;
    int PRIORITY_LOCAL = 90;

    int PRIORITY_SKELJS_VUE = 100;
    int PRIORITY_LOCAL_VUE = 110;

    Esm_jQuery jQuery = new Esm_jQuery(PRIORITY_BASE);
    Esm_vue vue = new Esm_vue(PRIORITY_BASE);

    Esm_datatables datatables = new Esm_datatables(PRIORITY_DATATABLES);

    Core core = new Core(PRIORITY_SKELJS);
    Dba dba = new Dba(PRIORITY_SKELJS);
    CoreVue coreVue = new CoreVue(PRIORITY_SKELJS_VUE);
    DbaVue dbaVue = new DbaVue(PRIORITY_SKELJS_VUE);

    EsmModule local = new EsmModule(null, PRIORITY_LOCAL);
    EsmModule localVue = new EsmModule(null, PRIORITY_LOCAL_VUE);

}
