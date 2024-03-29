package net.bodz.bas.fmt.xml.xq;

import org.w3c.dom.Element;

import net.bodz.bas.t.variant.IVariant;

public interface IElement
        extends
            IXmlSelection,
            Element {

    @Override
    String getAttribute(String name);

    IVariant getAttributeVar(String name);

    IElement getChild(String tagName);

    IElementMap getChildrenMap();

}
