package net.bodz.bas.fmt.xml.xq;

import org.w3c.dom.*;

public class QElement
        extends AbstractElements
        implements
            IElement {

    Element element;

    public QElement(Element element) {
        if (element == null)
            throw new NullPointerException("element");
        this.element = element;
    }

    public Element getWrapped() {
        return element;
    }

    @Override
    public int getNodeCount() {
        return 1;
    }

    @Override
    public int getElementCount() {
        return 1;
    }

    @Override
    public IElement getFirst() {
        return this;
    }

    @Override
    public ICachedIterable<Element> select() {
        return toList(element);
    }

    static CachedList<Element> toList(Element element) {
        CachedList<Element> list = new CachedList<>();
        if (element != null)
            list.add(element);
        return list;
    }

    public static QElement wrap(Element element) {
        return new QElement(element);
    }

    @Override
    public String getTagName() {
        return getWrapped().getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return getWrapped().getAttribute(name);
    }

    @Override
    public void setAttribute(String name, String value)
            throws DOMException {
        getWrapped().setAttribute(name, value);
    }

    @Override
    public void removeAttribute(String name)
            throws DOMException {
        getWrapped().removeAttribute(name);
    }

    @Override
    public Attr getAttributeNode(String name) {
        return getWrapped().getAttributeNode(name);
    }

    @Override
    public Attr setAttributeNode(Attr newAttr)
            throws DOMException {
        return getWrapped().setAttributeNode(newAttr);
    }

    @Override
    public Attr removeAttributeNode(Attr oldAttr)
            throws DOMException {
        return getWrapped().removeAttributeNode(oldAttr);
    }

    @Override
    public String getNodeName() {
        return getWrapped().getNodeName();
    }

    @Override
    public String getNodeValue()
            throws DOMException {
        return getWrapped().getNodeValue();
    }

    @Override
    public NodeList getElementsByTagName(String name) {
        return getWrapped().getElementsByTagName(name);
    }

    @Override
    public void setNodeValue(String nodeValue)
            throws DOMException {
        getWrapped().setNodeValue(nodeValue);
    }

    @Override
    public String getAttributeNS(String namespaceURI, String localName)
            throws DOMException {
        return getWrapped().getAttributeNS(namespaceURI, localName);
    }

    @Override
    public short getNodeType() {
        return getWrapped().getNodeType();
    }

    @Override
    public Node getParentNode() {
        return getWrapped().getParentNode();
    }

    @Override
    public NodeList getChildNodes() {
        return getWrapped().getChildNodes();
    }

    @Override
    public void setAttributeNS(String namespaceURI, String qualifiedName, String value)
            throws DOMException {
        getWrapped().setAttributeNS(namespaceURI, qualifiedName, value);
    }

    @Override
    public Node getFirstChild() {
        return getWrapped().getFirstChild();
    }

    @Override
    public Node getLastChild() {
        return getWrapped().getLastChild();
    }

    @Override
    public Node getPreviousSibling() {
        return getWrapped().getPreviousSibling();
    }

    @Override
    public Node getNextSibling() {
        return getWrapped().getNextSibling();
    }

    @Override
    public NamedNodeMap getAttributes() {
        return getWrapped().getAttributes();
    }

    @Override
    public Document getOwnerDocument() {
        return getWrapped().getOwnerDocument();
    }

    @Override
    public Node insertBefore(Node newChild, Node refChild)
            throws DOMException {
        return getWrapped().insertBefore(newChild, refChild);
    }

    @Override
    public void removeAttributeNS(String namespaceURI, String localName)
            throws DOMException {
        getWrapped().removeAttributeNS(namespaceURI, localName);
    }

    @Override
    public Node replaceChild(Node newChild, Node oldChild)
            throws DOMException {
        return getWrapped().replaceChild(newChild, oldChild);
    }

    @Override
    public Attr getAttributeNodeNS(String namespaceURI, String localName)
            throws DOMException {
        return getWrapped().getAttributeNodeNS(namespaceURI, localName);
    }

    @Override
    public Node removeChild(Node oldChild)
            throws DOMException {
        return getWrapped().removeChild(oldChild);
    }

    @Override
    public Attr setAttributeNodeNS(Attr newAttr)
            throws DOMException {
        return getWrapped().setAttributeNodeNS(newAttr);
    }

    @Override
    public Node appendChild(Node newChild)
            throws DOMException {
        return getWrapped().appendChild(newChild);
    }

    @Override
    public NodeList getElementsByTagNameNS(String namespaceURI, String localName)
            throws DOMException {
        return getWrapped().getElementsByTagNameNS(namespaceURI, localName);
    }

    @Override
    public boolean hasChildNodes() {
        return getWrapped().hasChildNodes();
    }

    @Override
    public Node cloneNode(boolean deep) {
        return getWrapped().cloneNode(deep);
    }

    @Override
    public boolean hasAttribute(String name) {
        return getWrapped().hasAttribute(name);
    }

    @Override
    public boolean hasAttributeNS(String namespaceURI, String localName)
            throws DOMException {
        return getWrapped().hasAttributeNS(namespaceURI, localName);
    }

    @Override
    public void normalize() {
        getWrapped().normalize();
    }

    @Override
    public TypeInfo getSchemaTypeInfo() {
        return getWrapped().getSchemaTypeInfo();
    }

    @Override
    public void setIdAttribute(String name, boolean isId)
            throws DOMException {
        getWrapped().setIdAttribute(name, isId);
    }

    @Override
    public boolean isSupported(String feature, String version) {
        return getWrapped().isSupported(feature, version);
    }

    @Override
    public void setIdAttributeNS(String namespaceURI, String localName, boolean isId)
            throws DOMException {
        getWrapped().setIdAttributeNS(namespaceURI, localName, isId);
    }

    @Override
    public String getNamespaceURI() {
        return getWrapped().getNamespaceURI();
    }

    @Override
    public String getPrefix() {
        return getWrapped().getPrefix();
    }

    @Override
    public void setIdAttributeNode(Attr idAttr, boolean isId)
            throws DOMException {
        getWrapped().setIdAttributeNode(idAttr, isId);
    }

    @Override
    public void setPrefix(String prefix)
            throws DOMException {
        getWrapped().setPrefix(prefix);
    }

    @Override
    public String getLocalName() {
        return getWrapped().getLocalName();
    }

    @Override
    public boolean hasAttributes() {
        return getWrapped().hasAttributes();
    }

    @Override
    public String getBaseURI() {
        return getWrapped().getBaseURI();
    }

    @Override
    public short compareDocumentPosition(Node other)
            throws DOMException {
        return getWrapped().compareDocumentPosition(other);
    }

    @Override
    public String getTextContent()
            throws DOMException {
        return getWrapped().getTextContent();
    }

    @Override
    public void setTextContent(String textContent)
            throws DOMException {
        getWrapped().setTextContent(textContent);
    }

    @Override
    public boolean isSameNode(Node other) {
        return getWrapped().isSameNode(other);
    }

    @Override
    public String lookupPrefix(String namespaceURI) {
        return getWrapped().lookupPrefix(namespaceURI);
    }

    @Override
    public boolean isDefaultNamespace(String namespaceURI) {
        return getWrapped().isDefaultNamespace(namespaceURI);
    }

    @Override
    public String lookupNamespaceURI(String prefix) {
        return getWrapped().lookupNamespaceURI(prefix);
    }

    @Override
    public boolean isEqualNode(Node arg) {
        return getWrapped().isEqualNode(arg);
    }

    @Override
    public Object getFeature(String feature, String version) {
        return getWrapped().getFeature(feature, version);
    }

    @Override
    public Object setUserData(String key, Object data, UserDataHandler handler) {
        return getWrapped().setUserData(key, data, handler);
    }

    @Override
    public Object getUserData(String key) {
        return getWrapped().getUserData(key);
    }

}
