package net.bodz.lily.model.base.schema.impl;

import net.bodz.bas.html.AbstractHtmlViewBuilder;
import net.bodz.bas.site.IBasicSiteAnchors;

import net.bodz.lily.model.base.schema.AbstractDefinition;

public abstract class AbstractDefinitionVbo<T extends AbstractDefinition<?>>
        extends AbstractHtmlViewBuilder<T>
        implements IBasicSiteAnchors {

    public AbstractDefinitionVbo(Class<?> valueClass, String... supportedFeatures) {
        super(valueClass, supportedFeatures);
    }

}
