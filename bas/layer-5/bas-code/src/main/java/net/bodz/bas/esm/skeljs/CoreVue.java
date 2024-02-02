package net.bodz.bas.esm.skeljs;

import net.bodz.bas.esm.EsmModule;
import net.bodz.bas.esm.EsmName;
import net.bodz.bas.esm.SourcePath;

public class CoreVue
        extends EsmModule {

    public CoreVue(int priority) {
        super("@skeljs/core", priority);
    }

    @SourcePath("src/ui/Dialog.vue")
    public class Dialog
            extends Source {
        public final EsmName Dialog = defaultExport("Dialog");
    }

    public final Dialog Dialog = new Dialog();

}
