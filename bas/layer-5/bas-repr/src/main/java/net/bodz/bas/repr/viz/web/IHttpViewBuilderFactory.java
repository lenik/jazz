package net.bodz.bas.repr.viz.web;

import net.bodz.bas.repr.viz.IViewBuilderFactory;
import net.bodz.bas.ui.dom1.IUiRef;

//@IndexedType(includeAbstract = true)
public interface IHttpViewBuilderFactory
        extends IViewBuilderFactory {

    String ATTRIBUTE_KEY = IHttpViewBuilderFactory.class.getName();

    @Override
    <T> IHttpViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features);

    @Override
    <T> IHttpViewBuilder<T> getViewBuilder(IUiRef<? extends T> ref, String... features);

}
