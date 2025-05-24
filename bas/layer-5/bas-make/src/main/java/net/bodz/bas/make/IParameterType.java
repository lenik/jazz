package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public interface IParameterType<Param> {

    @NotNull
    Class<Param> getParameterType();

}
