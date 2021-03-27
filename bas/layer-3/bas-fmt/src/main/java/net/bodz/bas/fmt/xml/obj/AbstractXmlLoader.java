package net.bodz.bas.fmt.xml.obj;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.IObjectXmlLoader;
import net.bodz.bas.fmt.xml.IXmlSerializable;

public abstract class AbstractXmlLoader
        implements
            IObjectXmlLoader {

    @Override
    public void loadXmlToObject(Object obj, Element element)
            throws ParseException, LoaderException {
        NamedNodeMap attributes = element.getAttributes();
        int n = attributes.getLength();
        for (int i = 0; i < n; i++) {
            Node attr = attributes.item(i);
            String name = attr.getNodeName();
            if (name == null)
                throw new NullPointerException("name");
            String value = attr.getNodeValue();
            attribute(name, value);
        }

        NodeList children = element.getChildNodes();
        n = children.getLength();
        for (int i = 0; i < n; i++) {
            Node childNode = children.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                String name = childNode.getNodeName();
                IXmlSerializable childObj = getChild(name);
                if (childObj == null)
                    continue;
                Element childElement = (Element) childNode;
                childObj.readObject(childElement);
            }
        }
    }

    protected abstract boolean attribute(String attributeName, String attributeData)
            throws ParseException, LoaderException;

    protected abstract IXmlSerializable getChild(String name)
            throws LoaderException;

}
