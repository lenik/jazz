package net.bodz.bas.program;

import net.bodz.bas.fn.IExecutableVarArgsX;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.program.model.IOptionsSupport;

@IndexedType
public interface IProgram
        extends
            IOptionsSupport,
            IExecutableVarArgsX<String, Exception> {

}
