package net.bodz.swt.viz;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.repr.viz.IViewBuilderFactory;
import net.bodz.bas.ui.dom1.IUiRef;

@IndexedType
public interface ISwtViewBuilderFactory
        extends IViewBuilderFactory {

    @Override
    <T> ISwtViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features);

    @Override
    <T> ISwtViewBuilder<T> getViewBuilder(IUiRef<? extends T> ref, String... features);

}
