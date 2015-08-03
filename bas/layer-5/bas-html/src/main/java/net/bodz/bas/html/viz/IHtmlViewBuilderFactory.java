package net.bodz.bas.html.viz;

import net.bodz.bas.http.viz.IHttpViewBuilderFactory;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.ui.dom1.IUiRef;

@IndexedType
public interface IHtmlViewBuilderFactory
        extends IHttpViewBuilderFactory {

    @Override
    <T> IHtmlViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features);

    @Override
    <T> IHtmlViewBuilder<T> getViewBuilder(IUiRef<? extends T> ref, String... features);

}
