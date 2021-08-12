package net.bodz.bas.fmt.xml.xq;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.bodz.bas.t.iterator.PrefetchedIterator;

public abstract class AbstractElements
        extends AbstractXmlSelection
        implements
            IElements {

    public abstract ICachedIterable<Element> select();

    @Override
    public Iterator<IElement> iterator() {
        Iterator<Element> iterator = select().iterator();
        return new Iterator<IElement>() {

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public IElement next() {
                Element element = iterator.next();
                return QElement.wrap(element);
            }

        };
    }

    @Override
    public String getVarSource() {
        StringBuilder sb = null;
        for (Element el : select()) {
            String content = el.getTextContent();
            if (sb == null)
                sb = new StringBuilder(content);
            else
                sb.append(content);
        }
        return sb == null ? null : sb.toString();
    }

    @Override
    public IXmlSelection a(String attributeName) {
        Iterable<Attr> attrs = () -> new PrefetchedIterator<Attr>() {
            Iterator<Element> eit = select().iterator();

            @Override
            protected Attr fetch() {
                while (eit.hasNext()) {
                    Element el = eit.next();
                    Attr attr = el.getAttributeNode(attributeName);
                    if (attr != null)
                        return attr;
                }
                return end();
            }
        };
        return new QAttrs(attrs);
    }

    @Override
    public IElement get(int index) {
        ICachedIterable<Element> selection = select();
        if (index < 0 || index >= selection.size())
            return null;
        Element el = selection.get(index);
        return new QElement(el);
    }

    @Override
    public IElements children() {
        Iterable<Element> union = () -> new PrefetchedIterator<Element>() {
            Iterator<Element> iterator = select().iterator();
            Iterator<Element> sub;

            @Override
            protected Element fetch() {
                while (true) {
                    while (sub != null) {
                        while (sub.hasNext()) {
                            Element el = sub.next();
                            // just exclude null items.
                            if (el != null)
                                return el;
                        }
                        sub = null;
                    }

                    if (!iterator.hasNext())
                        return end();

                    Element el = iterator.next();
                    sub = new ChildrenIterator(el);
                }
            }
        };

        return new QElements(union);
    }

    @Override
    public IElements select(Function<Element, NodeList> listing, Predicate<Element> predicate) {
        Iterable<Element> matches = () -> new PrefetchedIterator<Element>() {
            Iterator<Element> iterator = select().iterator();
            Iterator<Element> sub;

            @Override
            protected Element fetch() {
                while (true) {
                    while (sub != null) {
                        while (sub.hasNext()) {
                            Element el = sub.next();
                            if (el != null)
                                return el;
                        }
                        sub = null;
                    }

                    if (!iterator.hasNext())
                        return end();

                    Element el = iterator.next();
                    NodeList nodeList = listing.apply(el);
                    sub = filterElements(nodeList, predicate).iterator();
                }
            }
        };
        return new QElements(matches);
    }

    @Override
    public IElements select(Predicate<Element> predicate) {
        return select((Element el) -> el.getChildNodes(), predicate);
    }

    public static Iterable<Element> filterElements(NodeList list, Predicate<Element> predicate) {
        return () -> new PrefetchedIterator<Element>() {
            int index = 0;

            @Override
            protected Element fetch() {
                while (true) {
                    Node item = list.item(index++);
                    if (item == null)
                        return end();
                    if (item.getNodeType() != Node.ELEMENT_NODE)
                        continue;
                    Element el = (Element) item;
                    if (predicate.test(el))
                        return el;
                }
            }
        };
    }

}
