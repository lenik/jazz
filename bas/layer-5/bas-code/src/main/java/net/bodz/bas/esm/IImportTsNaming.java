package net.bodz.bas.esm;

import net.bodz.bas.codegen.IImportNaming;

public interface IImportTsNaming
        extends
            IImportNaming {

    String importName(EsmName name);

}
