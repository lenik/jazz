package net.bodz.bas.esm.skeljs;

import net.bodz.bas.esm.EsmModule;
import net.bodz.bas.esm.EsmName;
import net.bodz.bas.esm.SourcePath;

public class DbaVue
        extends EsmModule {

    public DbaVue(int priority) {
        super("@skeljs/dba", priority);
    }

    @SourcePath("src/ui/table/DataAdmin.vue")
    public class DataAdmin
            extends Source {
        public final EsmName DataAdmin = defaultExport("DataAdmin");
    }

    @SourcePath("src/ui/table/DataTable.vue")
    public class DataTable
            extends Source {
        public final EsmName DataTable = defaultExport("DataTable");
    }

    @SourcePath("src/ui/lily/LilyAdmin.vue")
    public class LilyAdmin
            extends Source {
        public final EsmName LilyAdmin = defaultExport("LilyAdmin");
    }

    @SourcePath("src/ui/lily/EntityChooseDialog.vue")
    public class EntityChooseDialog
            extends Source {
        public final EsmName EntityChooseDialog = defaultExport("EntityChooseDialog");
    }

    @SourcePath("src/ui/input/RefEditor.vue")
    public class RefEditor
            extends Source {
        public final EsmName RefEditor = defaultExport("RefEditor");
    }

    public final DataTable DataTable = new DataTable();
    public final DataAdmin DataAdmin = new DataAdmin();
    public final LilyAdmin LilyAdmin = new LilyAdmin();
    public final EntityChooseDialog EntityChooseDiaog = new EntityChooseDialog();
    public final RefEditor RefEditor = new RefEditor();

}
