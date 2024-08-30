package net.bodz.mda.xjdoc.taglib;

import java.util.function.Supplier;

import net.bodz.mda.xjdoc.model.IDocTag;

@FunctionalInterface
public interface IDocTagFactory
        extends
            Supplier<IDocTag<?>> {

}
