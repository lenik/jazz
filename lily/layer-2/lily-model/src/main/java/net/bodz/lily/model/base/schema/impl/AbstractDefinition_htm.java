package net.bodz.lily.model.base.schema.impl;

import net.bodz.bas.html.viz.AbstractHttpViewBuilder;
import net.bodz.bas.site.IBasicSiteAnchors;

import net.bodz.lily.model.base.schema.AbstractDefinition;

public abstract class AbstractDefinition_htm<T extends AbstractDefinition<?>>
        extends AbstractHttpViewBuilder<T>
        implements IBasicSiteAnchors {

    public AbstractDefinition_htm(Class<?> valueClass, String... supportedFeatures) {
        super(valueClass, supportedFeatures);
    }

}
