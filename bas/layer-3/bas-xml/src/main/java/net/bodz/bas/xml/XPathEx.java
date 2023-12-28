package net.bodz.bas.xml;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XPathEx
        extends DecoratedXPath {

    private static final long serialVersionUID = 1L;

    public XPathEx(XPath _orig) {
        super(_orig);
    }

    public Node evaluateNode(String expression, Object item)
            throws XPathExpressionException {
        Node node = (Node) evaluate(expression, item, XPathConstants.NODE);
        return node;
    }

    public NodeList evaluateNodes(String expression, Object item)
            throws XPathExpressionException {
        NodeList nodeList = (NodeList) evaluate(expression, item, XPathConstants.NODESET);
        return nodeList;
    }

    public final String text(String expression, Object item)
            throws XPathExpressionException {
        return evaluate(expression, item);
    }

    public final boolean test(String expression, Object item)
            throws XPathExpressionException {
        Boolean result = (Boolean) evaluate(expression, item, XPathConstants.BOOLEAN);
        return result.booleanValue();
    }

    public final Number number(String expression, Object item)
            throws XPathExpressionException {
        Number number = (Number) evaluate(expression, item, XPathConstants.NUMBER);
        return number;
    }

    public final Node get(String expression, Object item)
            throws XPathExpressionException {
        return evaluateNode(expression, item);
    }

    public final NodeList select(String expression, Object item)
            throws XPathExpressionException {
        return evaluateNodes(expression, item);
    }

}
