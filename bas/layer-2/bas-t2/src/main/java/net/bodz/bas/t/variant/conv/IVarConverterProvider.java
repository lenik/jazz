package net.bodz.bas.t.variant.conv;

import java.util.Collection;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IVarConverterProvider {

    Collection<IVarConverter<?>> getConverters();

}
