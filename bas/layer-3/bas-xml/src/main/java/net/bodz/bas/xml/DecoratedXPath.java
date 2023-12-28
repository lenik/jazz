package net.bodz.bas.xml;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathEvaluationResult;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;

import org.xml.sax.InputSource;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedXPath
        extends AbstractDecorator<XPath>
        implements
            XPath {

    private static final long serialVersionUID = 1L;

    public DecoratedXPath(XPath _orig) {
        super(_orig);
    }

    @Override
    public void reset() {
        getWrapped().reset();
    }

    @Override
    public void setXPathVariableResolver(XPathVariableResolver resolver) {
        getWrapped().setXPathVariableResolver(resolver);
    }

    @Override
    public XPathVariableResolver getXPathVariableResolver() {
        return getWrapped().getXPathVariableResolver();
    }

    @Override
    public void setXPathFunctionResolver(XPathFunctionResolver resolver) {
        getWrapped().setXPathFunctionResolver(resolver);
    }

    @Override
    public XPathFunctionResolver getXPathFunctionResolver() {
        return getWrapped().getXPathFunctionResolver();
    }

    @Override
    public void setNamespaceContext(NamespaceContext nsContext) {
        getWrapped().setNamespaceContext(nsContext);
    }

    @Override
    public NamespaceContext getNamespaceContext() {
        return getWrapped().getNamespaceContext();
    }

    @Override
    public XPathExpression compile(String expression)
            throws XPathExpressionException {
        return getWrapped().compile(expression);
    }

    @Override
    public Object evaluate(String expression, Object item, QName returnType)
            throws XPathExpressionException {
        return getWrapped().evaluate(expression, item, returnType);
    }

    @Override
    public String evaluate(String expression, Object item)
            throws XPathExpressionException {
        return getWrapped().evaluate(expression, item);
    }

    @Override
    public Object evaluate(String expression, InputSource source, QName returnType)
            throws XPathExpressionException {
        return getWrapped().evaluate(expression, source, returnType);
    }

    @Override
    public String evaluate(String expression, InputSource source)
            throws XPathExpressionException {
        return getWrapped().evaluate(expression, source);
    }

    @Override
    public <T> T evaluateExpression(String expression, Object item, Class<T> type)
            throws XPathExpressionException {
        return getWrapped().evaluateExpression(expression, item, type);
    }

    @Override
    public XPathEvaluationResult<?> evaluateExpression(String expression, Object item)
            throws XPathExpressionException {
        return getWrapped().evaluateExpression(expression, item);
    }

    @Override
    public <T> T evaluateExpression(String expression, InputSource source, Class<T> type)
            throws XPathExpressionException {
        return getWrapped().evaluateExpression(expression, source, type);
    }

    @Override
    public XPathEvaluationResult<?> evaluateExpression(String expression, InputSource source)
            throws XPathExpressionException {
        return getWrapped().evaluateExpression(expression, source);
    }

}
