package net.bodz.bas.make;

import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.order.IPriority;

public interface IGenericMakeRule<GT>
        extends IPriority {

    @NotNull
    IMakefile getMakefile();

    @NotNull
    GT getTarget();

    @NotNull
    Set<IGenericMakeTarget> getSources();

    @NotNull
    <T extends IMakeTarget> IMakeRule<T> realize(T target)
            throws RealizeException;

}
