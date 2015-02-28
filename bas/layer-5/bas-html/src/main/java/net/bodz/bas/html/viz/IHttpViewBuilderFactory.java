package net.bodz.bas.html.viz;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.repr.viz.IViewBuilderFactory;
import net.bodz.bas.ui.dom1.IUiRef;

@IndexedType
public interface IHttpViewBuilderFactory
        extends IViewBuilderFactory {

    @Override
    <T> IHttpViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features);

    @Override
    <T> IHttpViewBuilder<T> getViewBuilder(IUiRef<? extends T> ref, String... features);

}
