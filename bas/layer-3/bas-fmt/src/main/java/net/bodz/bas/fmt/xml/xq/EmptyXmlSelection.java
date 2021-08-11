package net.bodz.bas.fmt.xml.xq;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class EmptyXmlSelection
        extends AbstractXmlSelection
        implements
            IElements {

    @Override
    public String getVarSource() {
        return null;
    }

    @Override
    public IXmlSelection a(String attributeName) {
        return EMPTY;
    }

    @Override
    public int getNodeCount() {
        return 0;
    }

    @Override
    public int getElementCount() {
        return 0;
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

    @Override
    public IElements select(Predicate<Element> predicate) {
        return EMPTY;
    }

    @Override
    public IElements selectById(String id) {
        return EMPTY;
    }

    @Override
    public IElements selectByTag(String tag) {
        return EMPTY;
    }

    @Override
    public IElements selectByXpath(String xpath) {
        return EMPTY;
    }

    @Override
    public IElement getFirst() {
        return null;
    }

    @Override
    public IElement first()
            throws NoSuchElementException {
        throw new NoSuchElementException();
    }

    @Override
    public Iterator<IElement> iterator() {
        return Collections.emptyIterator();
    }

}
