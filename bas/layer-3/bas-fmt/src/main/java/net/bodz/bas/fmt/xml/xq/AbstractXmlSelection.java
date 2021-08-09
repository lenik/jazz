package net.bodz.bas.fmt.xml.xq;

import java.util.function.Function;
import java.util.function.Predicate;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import net.bodz.bas.t.variant.AbstractVariant;

public abstract class AbstractXmlSelection
        extends AbstractVariant
        implements
            IXmlSelection {

    @Override
    public final String get() {
        return getVarSource();
    }

    @Override
    public IElement get(int index) {
        return null;
    }

    @Override
    public IElements children() {
        return EMPTY;
    }

    @Override
    public IElements select(Function<Element, NodeList> listing, Predicate<Element> predicate) {
        return EMPTY;
    }

}
