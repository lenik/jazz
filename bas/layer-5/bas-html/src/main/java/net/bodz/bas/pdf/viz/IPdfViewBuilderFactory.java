package net.bodz.bas.pdf.viz;

import net.bodz.bas.http.viz.IHttpViewBuilderFactory;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.ui.dom1.IUiRef;

@IndexedType
public interface IPdfViewBuilderFactory
        extends IHttpViewBuilderFactory {

    @Override
    <T> IPdfViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features);

    @Override
    <T> IPdfViewBuilder<T> getViewBuilder(IUiRef<? extends T> ref, String... features);

}
