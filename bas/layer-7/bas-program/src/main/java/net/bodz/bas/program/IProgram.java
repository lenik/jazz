package net.bodz.bas.program;

import net.bodz.bas.fn.IExecutableVarArgsX;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.program.model.IOptionGroup;

@IndexedType
public interface IProgram
        extends IOptionGroup, IExecutableVarArgsX<String, Exception> {

}
