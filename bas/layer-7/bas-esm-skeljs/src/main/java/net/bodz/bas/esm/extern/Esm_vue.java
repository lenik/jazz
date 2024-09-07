package net.bodz.bas.esm.extern;

import net.bodz.bas.esm.EsmModule;
import net.bodz.bas.esm.EsmName;

public class Esm_vue
        extends EsmModule {

    public Esm_vue(int priority) {
        super("vue", priority);
    }

    public final EsmName computed = main.name("computed");
    public final EsmName inject = main.name("inject");
    public final EsmName provide = main.name("provide");
    public final EsmName Ref = main.type("ref");
    public final EsmName ref = main.name("ref");
    public final EsmName onMounted = main.name("onMounted");

}
