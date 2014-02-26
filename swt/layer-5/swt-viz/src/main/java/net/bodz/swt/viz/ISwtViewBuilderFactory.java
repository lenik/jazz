package net.bodz.swt.viz;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.repr.viz.IViewBuilderFactory;

@IndexedType
public interface ISwtViewBuilderFactory
        extends IViewBuilderFactory {

    @Override
    <T> ISwtViewBuilder<T> getViewBuilder(Class<? extends T> type);

}
