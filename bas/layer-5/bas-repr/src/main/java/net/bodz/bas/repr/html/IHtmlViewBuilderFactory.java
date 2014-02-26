package net.bodz.bas.repr.html;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.repr.viz.IViewBuilderFactory;

@IndexedType
public interface IHtmlViewBuilderFactory
        extends IViewBuilderFactory {

    @Override
    <T> IHtmlViewBuilder<T> getViewBuilder(Class<? extends T> type);

}
