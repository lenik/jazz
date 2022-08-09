package net.bodz.bas.fmt.xml.xq;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Predicate;

import org.w3c.dom.*;

import net.bodz.bas.t.variant.IVariant;

public class NullElement
        implements
            IElement {

    @Override
    public int getElementCount() {
        return 0;
    }

    @Override
    public IXmlSelection a(String attributeName) {
        return IXmlSelection.EMPTY;
    }

    @Override
    public IElement get(int index) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public IElements children() {
        return IElements.EMPTY;
    }

    @Override
    public IElements select(Function<Element, NodeList> listing, Predicate<Element> predicate) {
        return IElements.EMPTY;
    }

    @Override
    public String getVarSource() {
        return null;
    }

    @Override
    public String format(Object... args) {
        return "";
    }

    @Override
    public Object get() {
        return null;
    }

    @Override
    public Object get(Object defaultValue) {
        return defaultValue;
    }

    @Override
    public Object getScalar() {
        return null;
    }

    @Override
    public Object getScalar(Object defaultValue) {
        return defaultValue;
    }

    @Override
    public Object[] getArray() {
        return null;
    }

    @Override
    public Object[] getArray(Object[] defaultValue) {
        return defaultValue;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public String getString(String defaultString) {
        return defaultString;
    }

    @Override
    public String[] getStringArray() {
        return null;
    }

    @Override
    public String[] getStringArray(String[] defaultValue) {
        return defaultValue;
    }

    @Override
    public byte getByte() {
        return 0;
    }

    @Override
    public byte getByte(byte defaultValue) {
        return defaultValue;
    }

    @Override
    public Byte getByte(Byte defaultValue) {
        return defaultValue;
    }

    @Override
    public short getShort() {
        return 0;
    }

    @Override
    public short getShort(short defaultValue) {
        return defaultValue;
    }

    @Override
    public Short getShort(Short defaultValue) {
        return defaultValue;
    }

    @Override
    public int getInt() {
        return 0;
    }

    @Override
    public int getInt(int defaultValue) {
        return defaultValue;
    }

    @Override
    public Integer getInt(Integer defaultValue) {
        return defaultValue;
    }

    @Override
    public long getLong() {
        return 0;
    }

    @Override
    public long getLong(long defaultValue) {
        return defaultValue;
    }

    @Override
    public Long getLong(Long defaultValue) {
        return defaultValue;
    }

    @Override
    public float getFloat() {
        return 0;
    }

    @Override
    public float getFloat(float defaultValue) {
        return defaultValue;
    }

    @Override
    public Float getFloat(Float defaultValue) {
        return defaultValue;
    }

    @Override
    public double getDouble() {
        return 0;
    }

    @Override
    public double getDouble(double defaultValue) {
        return defaultValue;
    }

    @Override
    public Double getDouble(Double defaultValue) {
        return defaultValue;
    }

    @Override
    public boolean getBoolean() {
        return false;
    }

    @Override
    public boolean getBoolean(boolean defaultValue) {
        return defaultValue;
    }

    @Override
    public Boolean getBoolean(Boolean defaultValue) {
        return defaultValue;
    }

    @Override
    public char getChar() {
        return 0;
    }

    @Override
    public char getChar(char defaultValue) {
        return defaultValue;
    }

    @Override
    public Character getChar(Character defaultValue) {
        return defaultValue;
    }

    @Override
    public BigInteger getBigInteger() {
        return null;
    }

    @Override
    public BigInteger getBigInteger(BigInteger defaultValue) {
        return defaultValue;
    }

    @Override
    public BigDecimal getBigDecimal() {
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(BigDecimal defaultValue) {
        return defaultValue;
    }

    @Override
    public Date getDate() {
        return null;
    }

    @Override
    public Date getDate(Date defaultValue) {
        return defaultValue;
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> enumType) {
        return null;
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> enumType, T defaultValue) {
        return defaultValue;
    }

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    public String getAttribute(String name) {
        return null;
    }

    @Override
    public void setAttribute(String name, String value)
            throws DOMException {
    }

    @Override
    public void removeAttribute(String name)
            throws DOMException {
    }

    @Override
    public Attr getAttributeNode(String name) {
        return null;
    }

    @Override
    public Attr setAttributeNode(Attr newAttr)
            throws DOMException {
        return null;
    }

    @Override
    public Attr removeAttributeNode(Attr oldAttr)
            throws DOMException {
        return null;
    }

    @Override
    public NodeList getElementsByTagName(String name) {
        return NullNodeList.INSTANCE;
    }

    @Override
    public String getAttributeNS(String namespaceURI, String localName)
            throws DOMException {
        return null;
    }

    @Override
    public void setAttributeNS(String namespaceURI, String qualifiedName, String value)
            throws DOMException {
    }

    @Override
    public void removeAttributeNS(String namespaceURI, String localName)
            throws DOMException {
    }

    @Override
    public Attr getAttributeNodeNS(String namespaceURI, String localName)
            throws DOMException {
        return null;
    }

    @Override
    public Attr setAttributeNodeNS(Attr newAttr)
            throws DOMException {
        return null;
    }

    @Override
    public NodeList getElementsByTagNameNS(String namespaceURI, String localName)
            throws DOMException {
        return NullNodeList.INSTANCE;
    }

    @Override
    public boolean hasAttribute(String name) {
        return false;
    }

    @Override
    public boolean hasAttributeNS(String namespaceURI, String localName)
            throws DOMException {
        return false;
    }

    @Override
    public TypeInfo getSchemaTypeInfo() {
        return null; // TODO
    }

    @Override
    public void setIdAttribute(String name, boolean isId)
            throws DOMException {
    }

    @Override
    public void setIdAttributeNS(String namespaceURI, String localName, boolean isId)
            throws DOMException {
    }

    @Override
    public void setIdAttributeNode(Attr idAttr, boolean isId)
            throws DOMException {
    }

    @Override
    public String getNodeName() {
        return null;
    }

    @Override
    public String getNodeValue()
            throws DOMException {
        return null;
    }

    @Override
    public void setNodeValue(String nodeValue)
            throws DOMException {
    }

    @Override
    public short getNodeType() {
        return Node.COMMENT_NODE;
    }

    @Override
    public Node getParentNode() {
        return null;
    }

    @Override
    public NodeList getChildNodes() {
        return NullNodeList.INSTANCE;
    }

    @Override
    public Node getFirstChild() {
        return null;
    }

    @Override
    public Node getLastChild() {
        return null;
    }

    @Override
    public Node getPreviousSibling() {
        return null;
    }

    @Override
    public Node getNextSibling() {
        return null;
    }

    @Override
    public NamedNodeMap getAttributes() {
        return NullNamedNodeMap.INSTANCE;
    }

    @Override
    public Document getOwnerDocument() {
        return null;
    }

    @Override
    public Node insertBefore(Node newChild, Node refChild)
            throws DOMException {
        return null;
    }

    @Override
    public Node replaceChild(Node newChild, Node oldChild)
            throws DOMException {
        return null;
    }

    @Override
    public Node removeChild(Node oldChild)
            throws DOMException {
        return null;
    }

    @Override
    public Node appendChild(Node newChild)
            throws DOMException {
        return null;
    }

    @Override
    public boolean hasChildNodes() {
        return false;
    }

    @Override
    public Node cloneNode(boolean deep) {
        return this;
    }

    @Override
    public void normalize() {
    }

    @Override
    public boolean isSupported(String feature, String version) {
        return false;
    }

    @Override
    public String getNamespaceURI() {
        return null;
    }

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public void setPrefix(String prefix)
            throws DOMException {
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public boolean hasAttributes() {
        return false;
    }

    @Override
    public String getBaseURI() {
        return null;
    }

    @Override
    public short compareDocumentPosition(Node other)
            throws DOMException {
        return 0;
    }

    @Override
    public String getTextContent()
            throws DOMException {
        return null;
    }

    @Override
    public void setTextContent(String textContent)
            throws DOMException {
    }

    @Override
    public boolean isSameNode(Node other) {
        return false;
    }

    @Override
    public String lookupPrefix(String namespaceURI) {
        return null;
    }

    @Override
    public boolean isDefaultNamespace(String namespaceURI) {
        return false;
    }

    @Override
    public String lookupNamespaceURI(String prefix) {
        return null;
    }

    @Override
    public boolean isEqualNode(Node arg) {
        return false;
    }

    @Override
    public Object getFeature(String feature, String version) {
        return null;
    }

    @Override
    public Object setUserData(String key, Object data, UserDataHandler handler) {
        return null;
    }

    @Override
    public Object getUserData(String key) {
        return null;
    }

    @Override
    public IVariant getAttributeVar(String name) {
        return null;
    }

    @Override
    public IElement getChild(String tagName) {
        return null;
    }

    @Override
    public IElementMap getChildrenMap() {
        return NullElementMap.INSTANCE;
    }

    public static final NullElement INSTANCE = new NullElement();

}
