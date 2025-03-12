package net.bodz.bas.c.joox;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;

import org.joox.Content;
import org.joox.Context;
import org.joox.Each;
import org.joox.FastFilter;
import org.joox.Filter;
import org.joox.Mapper;
import org.joox.Match;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public interface IJOOX_static2
        extends
            IJOOX_static1 {

    /**
     * Wrap a new empty document
     */
    @Override
    Match $();

    /**
     * Wrap a JAXB-marshallable element in a jOOX {@link Match} element set
     *
     * @see #content(Object)
     * @see Match#content(Object)
     */
    @Override
    Match $(Object object);

    /**
     * Create a new DOM element in an independent document
     */
    @Override
    Match $(String name);

    /**
     * Create a new DOM element in an independent document
     */
    @Override
    Match $(String name, String content);

    /**
     * Create a new DOM element in an independent document
     * <p>
     * The added content is cloned into the new document
     */
    @Override
    Match $(String name, Element... content);

    /**
     * Create a new DOM element in an independent document
     * <p>
     * The added content is cloned into the new document
     */
    @Override
    Match $(String name, Match... content);

    /**
     * Wrap a DOM document in a jOOX {@link Match} element set
     */
    @Override
    Match $(Document document);

    /**
     * Wrap a DOM element in a jOOX {@link Match} element set
     */
    @Override
    Match $(Element element);

    /**
     * Wrap a DOM {@link Node} in a jOOX {@link Match} element set
     * <p>
     * Supported node types are
     * <ul>
     * <li>{@link Document} : see {@link #$(Document)}</li>
     * <li>{@link Element} : see {@link #$(Element)}</li>
     * </ul>
     * If the supplied Node is of any other type, then an empty Match is created
     */
    @Override
    Match $(Node node);

    /**
     * Wrap a DOM {@link NodeList} in a jOOX {@link Match} element set
     * <p>
     * If the supplied NodeList is empty or null, then an empty Match is created
     */
    @Override
    Match $(NodeList list);

    /**
     * Convenience method for calling <code>$(context.element())</code>
     */
    @Override
    Match $(Context context);

    /**
     * Convenience method for calling <code>$(match)</code>
     */
    @Override
    Match $(Match match);

    /**
     * Convenience method for calling <code>$(url.openStream())</code>
     */
    @Override
    Match $(URL url)
            throws SAXException, IOException;

    /**
     * Convenience method for calling <code>$(new File(uri))</code>
     */
    @Override
    Match $(URI uri)
            throws SAXException, IOException;

    /**
     * Read a DOM document from a file into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    @Override
    Match $(File file)
            throws SAXException, IOException;

    /**
     * Read a DOM document from a file into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    @Override
    Match $(Path path)
            throws SAXException, IOException;

    /**
     * Read a DOM document from a stream into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    @Override
    Match $(InputStream stream)
            throws SAXException, IOException;

    /**
     * Read a DOM document from a reader into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    @Override
    Match $(Reader reader)
            throws SAXException, IOException;

    /**
     * Read a DOM document from a file into a {@link Match} element set
     *
     * @throws IOException
     * @throws SAXException
     */
    @Override
    Match $(InputSource source)
            throws SAXException, IOException;

    /**
     * A filter that always returns false
     */
    FastFilter none();

    /**
     * A filter that always returns true
     */
    FastFilter all();

    /**
     * A filter that returns true on all even iteration indexes (starting with 0!)
     */
    FastFilter even();

    /**
     * A filter that returns true on all odd iteration indexes (starting with 0!)
     */
    FastFilter odd();

    /**
     * A filter that returns true on leaf elements
     */
    FastFilter leaf();

    /**
     * A filter that returns true on elements at given iteration indexes
     */
    FastFilter at(int... indexes);

    /**
     * A filter that returns all elements matched by a given selector.
     * <p>
     * In most cases, this is the same as calling {@link #tag(String)}. In
     * {@link Match#find(String)}, the following CSS-style selector syntax elements are also
     * supported:
     * <table border="1">
     * <tr>
     * <th>Selector pattern</th>
     * <th>meaning</th>
     * </tr>
     * <tr>
     * <td>*</td>
     * <td>any element</td>
     * </tr>
     * <tr>
     * <td>E</td>
     * <td>an element of type E</td>
     * </tr>
     * <tr>
     * <td>E[foo]</td>
     * <td>an E element with a "foo" attribute</td>
     * </tr>
     * <tr>
     * <td>E[foo="bar"]</td>
     * <td>an E element whose "foo" attribute value is exactly equal to "bar"</td>
     * </tr>
     * <tr>
     * <td>E[foo~="bar"]</td>
     * <td>an E element whose "foo" attribute value is a list of whitespace-separated values, one of
     * which is exactly equal to "bar"</td>
     * </tr>
     * <tr>
     * <td>E[foo^="bar"]</td>
     * <td>an E element whose "foo" attribute value begins exactly with the string "bar"</td>
     * </tr>
     * <tr>
     * <td>E[foo$="bar"]</td>
     * <td>an E element whose "foo" attribute value ends exactly with the string "bar"</td>
     * </tr>
     * <tr>
     * <td>E[foo*="bar"]</td>
     * <td>an E element whose "foo" attribute value contains the substring "bar"</td>
     * </tr>
     * <tr>
     * <td>E[foo|="en"]</td>
     * <td>an E element whose "foo" attribute has a hyphen-separated list of values beginning (from
     * the left) with "en"</td>
     * </tr>
     * <tr>
     * <td>E:root</td>
     * <td>an E element, root of the document</td>
     * </tr>
     * <tr>
     * <td>E:first-child</td>
     * <td>an E element, first child of its parent</td>
     * </tr>
     * <tr>
     * <td>E:last-child</td>
     * <td>an E element, last child of its parent</td>
     * </tr>
     * <tr>
     * <td>E:only-child</td>
     * <td>an E element, only child of its parent</td>
     * </tr>
     * <tr>
     * <td>E:empty</td>
     * <td>an E element that has no children (including text nodes)</td>
     * </tr>
     * <tr>
     * <td>E#myid</td>
     * <td>an E element with ID equal to "myid".</td>
     * </tr>
     * <tr>
     * <td>E F</td>
     * <td>an F element descendant of an E element</td>
     * </tr>
     * <tr>
     * <td>E > F</td>
     * <td>an F element child of an E element</td>
     * </tr>
     * <tr>
     * <td>E + F</td>
     * <td>an F element immediately preceded by an E element</td>
     * </tr>
     * <tr>
     * <td>E ~ F</td>
     * <td>an F element preceded by an E element</td>
     * </tr>
     * </table>
     * <p>
     * Note that due to the presence of pseudo selectors, such as <code>:root</code>,
     * <code>:empty</code>, etc, namespaces are not supported in selectors. Use jOOX's XPath
     * functionality provided in {@link Match#xpath(String)} along with
     * {@link Match#namespaces(java.util.Map)} if your XML document contains namespaces
     *
     * @see <a href=
     *      "http://www.w3.org/TR/selectors/#selectors">http://www.w3.org/TR/selectors/#selectors</a>
     */
    Filter selector(String selector);

    /**
     * A filter that returns all elements with a given tag name
     * <p>
     * This is the same as calling <code>tag(tagName, true)</code>
     *
     * @see #tag(String, boolean)
     */
    FastFilter tag(String tagName);

    /**
     * A filter that returns all elements with a given tag name
     * <p>
     * This method allows for specifying whether namespace prefixes should be ignored. This is
     * particularly useful in DOM Level 1 documents, which are namespace-unaware. In those methods
     * {@link Document#getElementsByTagNameNS(String, String)} will not work, as elements do not
     * contain any <code>localName</code>.
     *
     * @param tagName
     *            The tag name to match. Use <strong>*</strong> as a special tag name to match all
     *            tag names
     * @param ignoreNamespace
     *            Whether namespace prefixes can be ignored. When set to <code>true</code>, then the
     *            namespace prefix is ignored. When set to <code>false</code>, then
     *            <code>tagName</code> must include the actual namespace prefix.
     */
    FastFilter tag(String tagName, boolean ignoreNamespace);

    /**
     * A filter that returns all elements with a given namespace prefix
     * <p>
     * <code>null</code> and the empty string are treated equally to indicate that no namespace
     * prefix should be present.
     */
    FastFilter namespacePrefix(String namespacePrefix);

    /**
     * A filter that returns all elements with a given namespace URI
     * <p>
     * <code>null</code> and the empty string are treated equally to indicate that no namespace URI
     * should be present.
     * <p>
     * This only works if the underlying document is namespace-aware
     */
    FastFilter namespaceURI(String namespaceURI);

    /**
     * A filter that returns all elements whose text content matches a given regex
     *
     * @see Pattern#matches(String, CharSequence)
     */
    FastFilter matchText(String regex);

    /**
     * A filter that returns all elements whose text content matches a given regex
     *
     * @see Pattern#matches(String, CharSequence)
     */
    FastFilter matchAttr(String name, String valueRegex);

    /**
     * A filter that returns all elements whose tag name matches a given regex
     * <p>
     * This is the same as calling <code>matchTag(regex, true)</code>
     *
     * @see Pattern#matches(String, CharSequence)
     */
    FastFilter matchTag(String regex);

    /**
     * A filter that returns all elements whose tag name matches a given regex
     * <p>
     * This method allows for specifying whether namespace prefixes should be ignored. This is
     * particularly useful in DOM Level 1 documents, which are namespace-unaware. In those methods
     * {@link Document#getElementsByTagNameNS(String, String)} will not work, as elements do not
     * contain any <code>localName</code>.
     *
     * @param regex
     *            The regular expression to use for matching tag names.
     * @param ignoreNamespace
     *            Whether namespace prefixes can be ignored. When set to <code>true</code>, then the
     *            namespace prefix is ignored. When set to <code>false</code>, then
     *            <code>regex</code> must also match potential namespace prefixes.
     * @see Pattern#matches(String, CharSequence)
     */
    FastFilter matchTag(String regex, boolean ignoreNamespace);

    /**
     * A filter that returns all elements with a given attribute
     */
    FastFilter attr(String name);

    /**
     * A filter that returns all elements with a given attribute being set to a given value
     */
    FastFilter attr(String name, String value);

    /**
     * A filter that returns all elements with a given attribute being set to a given value
     */
    FastFilter attr(String name, String... values);

    /**
     * Combine filters
     */
    Filter and(Filter... filters);

    /**
     * Combine filters
     */
    Filter or(Filter... filters);

    /**
     * Inverse a filter
     */
    Filter not(Filter filter);

    /**
     * Create a filter matching id attributes
     */
    FastFilter ids(String... ids);

    /**
     * Get a constant content that returns the same <code>value</code> for all elements.
     */
    Content content(String value);

    /**
     * Get a constant content that returns a marshalled, JAXB-annotated <code>value</code> for all
     * elements.
     *
     * @see #$(Object)
     * @see Match#content(Object)
     */
    Content content(Object value);

    /**
     * Create a mapper that returns all <code>id</code> attributes
     */
    Mapper<String> ids();

    /**
     * Create a mapper that returns all attributes with a given name
     */
    Mapper<String> attrs(String attributeName);

    /**
     * Create a mapper that returns all paths to given elements
     */
    Mapper<String> paths();

    /**
     * Chain several instances of {@link Each} into a single one.
     * <p>
     * The resulting chained <code>Each</code> produces a new <code>Each</code> that can be used in
     * the {@link Match#each(Each)} method. I.e. every node in a set of matched nodes will be passed
     * to every chained <code>Each</code>, sequentially.
     */
    Each chain(Each... each);

    /**
     * Chain several instances of {@link Each} into a single one.
     * <p>
     * The resulting chained <code>Each</code> produces a new <code>Each</code> that can be used in
     * the {@link Match#each(Each)} method. I.e. every node in a set of matched nodes will be passed
     * to every chained <code>Each</code>, sequentially.
     */
    Each chain(Iterable<? extends Each> each);

    /**
     * Wrap a {@link NodeList} into an {@link Iterable}
     */
    Iterable<Element> iterable(NodeList elements);

    /**
     * Wrap a {@link NodeList} into an {@link Iterator}
     */
    Iterator<Element> iterator(NodeList elements);

    /**
     * Wrap a {@link NodeList} into an {@link List}
     */
    List<Element> list(NodeList elements);

    /**
     * Get a namespace-aware document builder
     */
    DocumentBuilder builder();

    /**
     * Convert a string value to any of these types:
     * <ul>
     * <li>{@link String}: The conversion has no effect</li>
     * <li>{@link Byte}: Numeric conversion. NaN will return null</li>
     * <li>{@link Short}: Numeric conversion. NaN will return null</li>
     * <li>{@link Integer}: Numeric conversion. NaN will return null</li>
     * <li>{@link Long}: Numeric conversion. NaN will return null</li>
     * <li>{@link Float}: Numeric conversion. NaN will return null</li>
     * <li>{@link Double}: Numeric conversion. NaN will return null</li>
     * <li>{@link BigDecimal}: Numeric conversion. NaN will return null</li>
     * <li>{@link BigInteger}: Numeric conversion. NaN will return null</li>
     * <li>{@link Boolean}: Boolean conversion. Boolean values for <code>true</code> are any of
     * these case-insensitive strings:
     * <ul>
     * <li><code>1</code></li>
     * <li><code>y</code></li>
     * <li><code>yes</code></li>
     * <li><code>true</code></li>
     * <li><code>on</code></li>
     * <li><code>enabled</code></li>
     * </ul>
     * Boolean values for <code>false</code> are any of these case-insensitive strings:
     * <ul>
     * <li><code>0</code></li>
     * <li><code>n</code></li>
     * <li><code>no</code></li>
     * <li><code>false</code></li>
     * <li><code>off</code></li>
     * <li><code>disabled</code></li>
     * </ul>
     * </li>
     * <li>Primitive types: Numeric or boolean conversion, except that <code>null</code> and illegal
     * values will result in <code>0</code> or <code>false</code></li>
     * <li>{@link java.util.Date}: Datetime conversion.</li>
     * <li>{@link java.util.Calendar}: Datetime conversion.</li>
     * <li>{@link java.util.GregorianCalendar}: Datetime conversion.</li>
     * <li>{@link java.sql.Timestamp}: Datetime conversion. Possible patterns for datetime
     * conversion are
     * <ul>
     * <li><code>yyyy</code>: Only the year is parsed</li>
     * <li><code>yyyy[-/]MM</code>: Year and month are parsed. Separator characters are
     * optional</li>
     * <li><code>yyyy[-/]MM[-/]dd</code>: Date is parsed. Separator characters are optional</li>
     * <li><code>dd[-/.]MM[-/.]yyyy</code>: Date is parsed. Separator characters are mandatory</li>
     * <li><code>yyyy[-/]MM[-/]dd[T ]HH</code>: Date and hour are parsed. Separator characters are
     * optional</li>
     * <li><code>yyyy[-/]MM[-/]dd[T ]HH[:]mm</code>: Date and time are parsed. Separator characters
     * are optional</li>
     * <li><code>yyyy[-/]MM[-/]dd[T ]HH[:]mm[:]ss</code>: Date and time are parsed. Separator
     * characters are optional</li>
     * <li><code>yyyy[-/]MM[-/]dd[T ]HH[:]mm[:]ss.SSS</code>: Date and time are parsed. Separator
     * characters are optional</li>
     * </ul>
     * </li>
     * <li>{@link java.sql.Date}: Date conversion. Possible patterns for date conversion are
     * <ul>
     * <li><code>yyyy</code>: Only the year is parsed</li>
     * <li><code>yyyy[-/]MM</code>: Year and month are parsed. Separator characters are
     * optional</li>
     * <li><code>yyyy[-/]MM[-/]dd</code>: Date is parsed. Separator characters are optional</li>
     * <li><code>dd[-/.]MM[-/.]yyyy</code>: Date is parsed. Separator characters are mandatory</li>
     * </ul>
     * </li>
     * <li>{@link java.sql.Time}: Time conversion. Possible patterns for time conversion are
     * <ul>
     * <li><code>HH</code>: Hour is parsed. Separator characters are optional</li>
     * <li><code>HH[:]mm</code>: Hour and minute are parsed. Separator characters are optional</li>
     * <li><code>HH[:]mm[:]ss</code>: Time is parsed. Separator characters are optional</li>
     * </ul>
     * </li>
     * <li>Any of the above as array. Arrays of any type are split by any whitespace character,
     * comma or semi-colon. String literals may be delimited by quotes as well.</li>
     * </ul>
     * <p>
     * All other values evaluate to <code>null</code>
     */
    <T> T convert(String value, Class<T> type);

    /**
     * Convert several values
     *
     * @see #convert(String, Class)
     */
    <T> List<T> convert(List<String> values, Class<T> type);

}