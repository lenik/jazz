package net.bodz.bas.xml;

import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class XPathFn {

    private static XPathFactory defaultFactory = XPathFactory.newInstance();
    private static XPathEx defaultXPath = new XPathEx(defaultFactory.newXPath());

    public static XPathEx getXPath() {
        return defaultXPath;
    }

    public static XPathExpression compile(String expr)
            throws XPathExpressionException {
        XPathExpression xPathExpr = defaultXPath.compile(expr);
        return xPathExpr;
    }

}
