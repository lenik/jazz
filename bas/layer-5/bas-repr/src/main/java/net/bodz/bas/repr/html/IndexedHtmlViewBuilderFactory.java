package net.bodz.bas.repr.html;

import java.util.ServiceLoader;

public class IndexedHtmlViewBuilderFactory
        extends AbstractHtmlViewBuilderFactory {

    public IndexedHtmlViewBuilderFactory() {
        for (IHtmlViewBuilder<?> vbo : ServiceLoader.load(IHtmlViewBuilder.class)) {
            for (Class<?> supportedClass : vbo.getSupportedClasses())
                addViewBuilder(supportedClass, vbo);
        }
    }

    @Override
    public <T> IHtmlViewBuilder<T> getViewBuilder(Class<? extends T> type) {
        return super.getViewBuilder(type);
    }

}
