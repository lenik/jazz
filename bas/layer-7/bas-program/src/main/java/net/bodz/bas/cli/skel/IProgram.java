package net.bodz.bas.cli.skel;

import net.bodz.bas.cli.model.IOptionGroup;
import net.bodz.bas.fn.IExecutableVarArgsX;

public interface IProgram
        extends IOptionGroup, IExecutableVarArgsX<String, Exception> {

}
