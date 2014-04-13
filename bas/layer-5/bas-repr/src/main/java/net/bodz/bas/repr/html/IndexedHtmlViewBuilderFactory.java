package net.bodz.bas.repr.html;

import java.util.ServiceLoader;

public class IndexedHtmlViewBuilderFactory
        extends AbstractHtmlViewBuilderFactory {

    public IndexedHtmlViewBuilderFactory() {
        for (IHtmlViewBuilder<?> vbo : ServiceLoader.load(IHtmlViewBuilder.class))
            addViewBuilder(vbo);
    }

    private static IndexedHtmlViewBuilderFactory instance;

    public static IndexedHtmlViewBuilderFactory getInstance() {
        if (instance == null) {
            synchronized (IndexedHtmlViewBuilderFactory.class) {
                if (instance == null)
                    instance = new IndexedHtmlViewBuilderFactory();
            }
        }
        return instance;
    }

}
