package net.bodz.bas.fmt.xml.xq;

import java.util.List;

import org.w3c.dom.Element;

public class QElements
        extends AbstractElements {

    ICachedIterable<Element> elements;

    public QElements(ICachedIterable<Element> elements) {
        if (elements == null)
            throw new NullPointerException("elements");
        this.elements = elements;
    }

    public QElements(Iterable<Element> elements) {
        this(new DefaultCachedIterable<>(elements));
    }

    public QElements(List<Element> elements) {
        this((ICachedIterable<Element>) new CachedList<Element>(elements));
    }

    public QElements(CachedList<Element> elements) {
        this((ICachedIterable<Element>) elements);
    }

    @Override
    public ICachedIterable<Element> select() {
        return elements;
    }

    @Override
    public int getElementCount() {
        return elements.size();
    }

    @Override
    public IElement getFirst() {
        for (Element el : elements)
            return QElement.wrap(el);
        return null;
    }

}
