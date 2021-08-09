package net.bodz.bas.fmt.xml.xq;

import java.util.function.Function;
import java.util.function.Predicate;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public interface IXmlSelection
        extends
            IStringVar {

    IXmlSelection a(String attributeName);

    IElement get(int index);

    IElements children();

    IElements select(Function<Element, NodeList> listing, Predicate<Element> predicate);

    default IElements select(Predicate<Element> predicate) {
        return select((Element el) -> el.getChildNodes(), predicate);
    }

    default IElements selectById(String id) {
        return select((Element el) -> id.equals(el.getAttribute("id")));
    }

    default IElements selectByTag(String tag) {
        return select((Element el) -> el.getElementsByTagName(tag), (Element val) -> true);
    }

    default IElements selectByXpath(String xpath) {
        throw new UnsupportedOperationException();
    }

    EmptyXmlSelection EMPTY = new EmptyXmlSelection();

}
