package net.bodz.bas.repr.html;

import java.util.ServiceLoader;

public class IndexedHtmlViewBuilderFactory
        extends AbstractHtmlViewBuilderFactory {

    public IndexedHtmlViewBuilderFactory() {
        for (IHtmlViewBuilder<?> vbo : ServiceLoader.load(IHtmlViewBuilder.class))
            addViewBuilder(vbo);
    }

}
