package net.bodz.bas.xml.dom;

import java.util.Collection;

public interface IXmlNode {

    XmlNodeType getType();

    IXmlTag getParent();

    IXmlNode attach(IXmlTag parent);

    IXmlNode detach();

    Collection<? extends IXmlNode> getChildren();

    int getPosition();

}
