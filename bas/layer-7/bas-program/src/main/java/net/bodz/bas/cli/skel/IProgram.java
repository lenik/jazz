package net.bodz.bas.cli.skel;

import net.bodz.bas.cli.model.IOptionGroup;
import net.bodz.bas.model.IExecutableVarArgsX;

public interface IProgram
        extends IOptionGroup, IExecutableVarArgsX<String, Exception> {

}
