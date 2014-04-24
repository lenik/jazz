package net.bodz.bas.html;

import java.util.ServiceLoader;

/**
 * @see IHtmlViewBuilder The indexed type.
 */
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
