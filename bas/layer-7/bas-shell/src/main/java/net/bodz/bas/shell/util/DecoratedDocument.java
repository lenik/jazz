package net.bodz.bas.shell.util;

import org.w3c.dom.*;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedDocument
        extends AbstractDecorator<Document>
        implements Document {

    private static final long serialVersionUID = 1L;

    public DecoratedDocument(Document _orig) {
        super(_orig);
    }

    public DocumentType getDoctype() {
        return getWrapped().getDoctype();
    }

    public DOMImplementation getImplementation() {
        return getWrapped().getImplementation();
    }

    public Element getDocumentElement() {
        return getWrapped().getDocumentElement();
    }

    public Element createElement(String tagName)
            throws DOMException {
        return getWrapped().createElement(tagName);
    }

    public DocumentFragment createDocumentFragment() {
        return getWrapped().createDocumentFragment();
    }

    public Text createTextNode(String data) {
        return getWrapped().createTextNode(data);
    }

    public Comment createComment(String data) {
        return getWrapped().createComment(data);
    }

    public CDATASection createCDATASection(String data)
            throws DOMException {
        return getWrapped().createCDATASection(data);
    }

    public ProcessingInstruction createProcessingInstruction(String target, String data)
            throws DOMException {
        return getWrapped().createProcessingInstruction(target, data);
    }

    public Attr createAttribute(String name)
            throws DOMException {
        return getWrapped().createAttribute(name);
    }

    public String getNodeName() {
        return getWrapped().getNodeName();
    }

    public String getNodeValue()
            throws DOMException {
        return getWrapped().getNodeValue();
    }

    public EntityReference createEntityReference(String name)
            throws DOMException {
        return getWrapped().createEntityReference(name);
    }

    public void setNodeValue(String nodeValue)
            throws DOMException {
        getWrapped().setNodeValue(nodeValue);
    }

    public short getNodeType() {
        return getWrapped().getNodeType();
    }

    public Node getParentNode() {
        return getWrapped().getParentNode();
    }

    public NodeList getChildNodes() {
        return getWrapped().getChildNodes();
    }

    public Node getFirstChild() {
        return getWrapped().getFirstChild();
    }

    public NodeList getElementsByTagName(String tagname) {
        return getWrapped().getElementsByTagName(tagname);
    }

    public Node getLastChild() {
        return getWrapped().getLastChild();
    }

    public Node getPreviousSibling() {
        return getWrapped().getPreviousSibling();
    }

    public Node getNextSibling() {
        return getWrapped().getNextSibling();
    }

    public NamedNodeMap getAttributes() {
        return getWrapped().getAttributes();
    }

    public Node importNode(Node importedNode, boolean deep)
            throws DOMException {
        return getWrapped().importNode(importedNode, deep);
    }

    public Document getOwnerDocument() {
        return getWrapped().getOwnerDocument();
    }

    public Node insertBefore(Node newChild, Node refChild)
            throws DOMException {
        return getWrapped().insertBefore(newChild, refChild);
    }

    public Node replaceChild(Node newChild, Node oldChild)
            throws DOMException {
        return getWrapped().replaceChild(newChild, oldChild);
    }

    public Node removeChild(Node oldChild)
            throws DOMException {
        return getWrapped().removeChild(oldChild);
    }

    public Node appendChild(Node newChild)
            throws DOMException {
        return getWrapped().appendChild(newChild);
    }

    public Element createElementNS(String namespaceURI, String qualifiedName)
            throws DOMException {
        return getWrapped().createElementNS(namespaceURI, qualifiedName);
    }

    public boolean hasChildNodes() {
        return getWrapped().hasChildNodes();
    }

    public Node cloneNode(boolean deep) {
        return getWrapped().cloneNode(deep);
    }

    public void normalize() {
        getWrapped().normalize();
    }

    public Attr createAttributeNS(String namespaceURI, String qualifiedName)
            throws DOMException {
        return getWrapped().createAttributeNS(namespaceURI, qualifiedName);
    }

    public boolean isSupported(String feature, String version) {
        return getWrapped().isSupported(feature, version);
    }

    public String getNamespaceURI() {
        return getWrapped().getNamespaceURI();
    }

    public String getPrefix() {
        return getWrapped().getPrefix();
    }

    public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        return getWrapped().getElementsByTagNameNS(namespaceURI, localName);
    }

    public void setPrefix(String prefix)
            throws DOMException {
        getWrapped().setPrefix(prefix);
    }

    public Element getElementById(String elementId) {
        return getWrapped().getElementById(elementId);
    }

    public String getInputEncoding() {
        return getWrapped().getInputEncoding();
    }

    public String getXmlEncoding() {
        return getWrapped().getXmlEncoding();
    }

    public boolean getXmlStandalone() {
        return getWrapped().getXmlStandalone();
    }

    public String getLocalName() {
        return getWrapped().getLocalName();
    }

    public void setXmlStandalone(boolean xmlStandalone)
            throws DOMException {
        getWrapped().setXmlStandalone(xmlStandalone);
    }

    public boolean hasAttributes() {
        return getWrapped().hasAttributes();
    }

    public String getBaseURI() {
        return getWrapped().getBaseURI();
    }

    public String getXmlVersion() {
        return getWrapped().getXmlVersion();
    }

    public short compareDocumentPosition(Node other)
            throws DOMException {
        return getWrapped().compareDocumentPosition(other);
    }

    public void setXmlVersion(String xmlVersion)
            throws DOMException {
        getWrapped().setXmlVersion(xmlVersion);
    }

    public String getTextContent()
            throws DOMException {
        return getWrapped().getTextContent();
    }

    public boolean getStrictErrorChecking() {
        return getWrapped().getStrictErrorChecking();
    }

    public void setStrictErrorChecking(boolean strictErrorChecking) {
        getWrapped().setStrictErrorChecking(strictErrorChecking);
    }

    public void setTextContent(String textContent)
            throws DOMException {
        getWrapped().setTextContent(textContent);
    }

    public String getDocumentURI() {
        return getWrapped().getDocumentURI();
    }

    public void setDocumentURI(String documentURI) {
        getWrapped().setDocumentURI(documentURI);
    }

    public Node adoptNode(Node source)
            throws DOMException {
        return getWrapped().adoptNode(source);
    }

    public boolean isSameNode(Node other) {
        return getWrapped().isSameNode(other);
    }

    public String lookupPrefix(String namespaceURI) {
        return getWrapped().lookupPrefix(namespaceURI);
    }

    public boolean isDefaultNamespace(String namespaceURI) {
        return getWrapped().isDefaultNamespace(namespaceURI);
    }

    public String lookupNamespaceURI(String prefix) {
        return getWrapped().lookupNamespaceURI(prefix);
    }

    public boolean isEqualNode(Node arg) {
        return getWrapped().isEqualNode(arg);
    }

    public DOMConfiguration getDomConfig() {
        return getWrapped().getDomConfig();
    }

    public void normalizeDocument() {
        getWrapped().normalizeDocument();
    }

    public Node renameNode(Node n, String namespaceURI, String qualifiedName)
            throws DOMException {
        return getWrapped().renameNode(n, namespaceURI, qualifiedName);
    }

    public Object getFeature(String feature, String version) {
        return getWrapped().getFeature(feature, version);
    }

    public Object setUserData(String key, Object data, UserDataHandler handler) {
        return getWrapped().setUserData(key, data, handler);
    }

    public Object getUserData(String key) {
        return getWrapped().getUserData(key);
    }

}
