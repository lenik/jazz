package net.bodz.bas.html.io;

import java.util.Map;

import net.bodz.bas.html.io.tag.*;
import net.bodz.bas.io.xml.IXmlOut;
import net.bodz.bas.ui.css3.property.DirectionMode;

public interface IHtmlOut
        extends IXmlOut {

    @Override
    HtmlDoc getDoc();

    @Override
    IHtmlOut begin(String name);

    @Override
    IHtmlOut end();

    @Override
    IHtmlOut attrs(Map<String, ?> attributes);

    @Override
    IHtmlOut attr(String name, String value);

    @Override
    IHtmlOut attr(String name, Object value);

    @Override
    IHtmlOut text(String str);

    @Override
    IHtmlOut text(Object str);

    @Override
    IHtmlOut textln(String str);

    @Override
    IHtmlOut textln(Object str);

    @Override
    IHtmlOut cdata(String cdata);

    @Override
    IHtmlOut pi(String target, String data);

    @Override
    IHtmlOut comment(String str);

    /**
     * The id attribute specifies its element's unique identifier (ID). [DOM]
     * <p>
     * The value must be unique amongst all the IDs in the element's home subtree and must contain
     * at least one character. The value must not contain any space characters.
     */
    IHtmlOut id(String id);

    // HTML5 Global Attributes

    /**
     * All HTML elements may have the accesskey content attribute set. The accesskey attribute's
     * value is used by the user agent as a guide for creating a keyboard shortcut that activates or
     * focuses the element.
     * <p>
     * If specified, the value must be an ordered set of unique space-separated tokens that are
     * case-sensitive, each of which must be exactly one Unicode code point in length.
     */
    IHtmlOut accesskey(String accesskey);

    /**
     * Every HTML element may have a class attribute specified.
     * <p>
     * The attribute, if specified, must have a value that is a set of space-separated tokens
     * representing the various classes that the element belongs to.
     * <p>
     * The classes that an HTML element has assigned to it consists of all the classes returned when
     * the value of the class attribute is split on spaces. (Duplicates are ignored.)
     */
    IHtmlOut class_(String class_);

    /**
     * The contenteditable attribute is an enumerated attribute whose keywords are the empty string,
     * true, and false. The empty string and the true keyword map to the true state. The false
     * keyword maps to the false state. In addition, there is a third state, the inherit state,
     * which is the missing value default (and the invalid value default).
     */
    IHtmlOut contenteditable(Boolean contenteditable);

    /**
     * The dir attribute specifies the element's text directionality. The attribute is an enumerated
     * attribute with the following keywords and states:
     */
    IHtmlOut dir(DirectionMode dir);

    /**
     * All HTML elements may have the draggable content attribute set. The draggable attribute is an
     * enumerated attribute. It has three states. The first state is true and it has the keyword
     * true. The second state is false and it has the keyword false. The third state is auto; it has
     * no keywords but it is the missing value default.
     */
    IHtmlOut draggable(Boolean draggable);

    /**
     * All HTML elements may have the dropzone content attribute set. When specified, its value must
     * be an unordered set of unique space-separated tokens that are ASCII case-insensitive. The
     * allowed values are the following:
     * 
     * <dl>
     * <dt><code>copy</code></dt>
     * <dd>
     * Indicates that dropping an accepted item on the element will result in a copy of the dragged
     * data.</dd>
     * <dt><code>move</code></dt>
     * <dd>
     * Indicates that dropping an accepted item on the element will result in the dragged data being
     * moved to the new location.</dd>
     * <dt><code>link</code></dt>
     * <dd>
     * Indicates that dropping an accepted item on the element will result in a link to the original
     * data.</dd>
     * </dl>
     */
    IHtmlOut dropzone(String dropzone);

    /**
     * All HTML elements may have the hidden content attribute set. The hidden attribute is a
     * boolean attribute. When specified on an element, it indicates that the element is not yet, or
     * is no longer, directly relevant to the page's current state, or that it is being used to
     * declare content to be reused by other parts of the page as opposed to being directly accessed
     * by the user. User agents should not render elements that have the hidden attribute specified.
     * This requirement may be implemented indirectly through the style layer. For example, an
     * HTML+CSS user agent could implement these requirements using the rules suggested in the
     * Rendering section.
     */
    IHtmlOut hidden(Boolean hidden);

    /**
     * The lang attribute (in no namespace) specifies the primary language for the element's
     * contents and for any of the element's attributes that contain text. Its value must be a valid
     * BCP 47 language tag, or the empty string. Setting the attribute to the empty string indicates
     * that the primary language is unknown. [BCP47]
     */
    IHtmlOut lang(String lang);

    /**
     * The spellcheck attribute is an enumerated attribute whose keywords are the empty string, true
     * and false. The empty string and the true keyword map to the true state. The false keyword
     * maps to the false state. In addition, there is a third state, the default state, which is the
     * missing value default (and the invalid value default).
     * <p>
     * Note: The true state indicates that the element is to have its spelling and grammar checked.
     * The default state indicates that the element is to act according to a default behavior,
     * possibly based on the parent element's own spellcheck state, as defined below. The false
     * state indicates that the element is not to be checked.
     */
    IHtmlOut spellcheck(String spellcheck);

    /**
     * All HTML elements may have the style content attribute set. This is a CSS styling attribute
     * as defined by the CSS Styling Attribute Syntax specification. [CSSATTR]
     */
    IHtmlOut style(String style);

    /**
     * The tabindex content attribute allows authors to control whether an element is supposed to be
     * focusable, whether it is supposed to be reachable using sequential focus navigation, and what
     * is to be the relative order of the element for the purposes of sequential focus navigation.
     * The name "tab index" comes from the common use of the "tab" key to navigate through the
     * focusable elements. The term "tabbing" refers to moving forward through the focusable
     * elements that can be reached using sequential focus navigation.
     * <p>
     * The tabindex attribute, if specified, must have a value that is a valid integer.
     * <p>
     * Each element can have a tabindex focus flag set, as defined below. This flag is a factor that
     * contributes towards determining whether an element is focusable, as described in the next
     * section.
     */
    IHtmlOut tabindex(Integer tabindex);

    /**
     * The title attribute represents advisory information for the element, such as would be
     * appropriate for a tooltip. On a link, this could be the title or a description of the target
     * resource; on an image, it could be the image credit or a description of the image; on a
     * paragraph, it could be a footnote or commentary on the text; on a citation, it could be
     * further information about the source; on interactive content, it could be a label for, or
     * instructions for, use of the element; and so forth. The value is text.
     */
    IHtmlOut title(String title);

    /**
     * The translate attribute is an enumerated attribute that is used to specify whether an
     * element's attribute values and the values of its Text node children are to be translated when
     * the page is localized, or whether to leave them unchanged.
     * <p>
     * The attribute's keywords are the empty string, yes, and no. The empty string and the yes
     * keyword map to the yes state. The no keyword maps to the no state. In addition, there is a
     * third state, the inherit state, which is the missing value default (and the invalid value
     * default).
     * <p>
     * The following attributes are translatable attributes:
     * <ul>
     * <li>abbr on th elements
     * <li>alt on area, img, and input elements
     * <li>content on meta elements, if the name attribute specifies a metadata name whose value is
     * known to be translatable
     * <li>download on a and area elements
     * <li>label on optgroup, option, and track elements
     * <li>lang on HTML elements; must be "translated" to match the language used in the translation
     * <li>placeholder on input and textarea elements
     * <li>srcdoc on iframe elements; must be parsed and recursively processed
     * <li>style on HTML elements; must be parsed and recursively processed (e.g. for the values of
     * 'content' properties)
     * <li>title on all HTML elements
     * <li>value on input elements with a type attribute in the Button state or the Reset Button
     * state
     * </ul>
     */
    IHtmlOut translate(Boolean translate);

    // Events

    IHtmlOut onblur(String handler);

    IHtmlOut oncancel(String handler);

    IHtmlOut oncanplay(String handler);

    IHtmlOut oncanplaythrough(String handler);

    IHtmlOut onchange(String handler);

    IHtmlOut onclick(String handler);

    IHtmlOut onclose(String handler);

    IHtmlOut oncuechange(String handler);

    IHtmlOut ondblclick(String handler);

    IHtmlOut ondrag(String handler);

    IHtmlOut ondragend(String handler);

    IHtmlOut ondragenter(String handler);

    IHtmlOut ondragexit(String handler);

    IHtmlOut ondragleave(String handler);

    IHtmlOut ondragover(String handler);

    IHtmlOut ondragstart(String handler);

    IHtmlOut ondrop(String handler);

    IHtmlOut ondurationchange(String handler);

    IHtmlOut onemptied(String handler);

    IHtmlOut onended(String handler);

    IHtmlOut onerror(String handler);

    IHtmlOut onfocus(String handler);

    IHtmlOut oninput(String handler);

    IHtmlOut oninvalid(String handler);

    IHtmlOut onkeydown(String handler);

    IHtmlOut onkeypress(String handler);

    IHtmlOut onkeyup(String handler);

    IHtmlOut onload(String handler);

    IHtmlOut onloadeddata(String handler);

    IHtmlOut onloadedmetadata(String handler);

    IHtmlOut onloadstart(String handler);

    IHtmlOut onmousedown(String handler);

    IHtmlOut onmouseenter(String handler);

    IHtmlOut onmouseleave(String handler);

    IHtmlOut onmousemove(String handler);

    IHtmlOut onmouseout(String handler);

    IHtmlOut onmouseover(String handler);

    IHtmlOut onmouseup(String handler);

    IHtmlOut onmousewheel(String handler);

    IHtmlOut onpause(String handler);

    IHtmlOut onplay(String handler);

    IHtmlOut onplaying(String handler);

    IHtmlOut onprogress(String handler);

    IHtmlOut onratechange(String handler);

    IHtmlOut onreset(String handler);

    IHtmlOut onresize(String handler);

    IHtmlOut onscroll(String handler);

    IHtmlOut onseeked(String handler);

    IHtmlOut onseeking(String handler);

    IHtmlOut onselect(String handler);

    IHtmlOut onshow(String handler);

    IHtmlOut onstalled(String handler);

    IHtmlOut onsubmit(String handler);

    IHtmlOut onsuspend(String handler);

    IHtmlOut ontimeupdate(String handler);

    IHtmlOut ontoggle(String handler);

    IHtmlOut onvolumechange(String handler);

    IHtmlOut onwaiting(String handler);

    // HTML Tags
    HtmlA a();

    HtmlAbbr abbr();

    HtmlAddress address();

    HtmlArea area();

    HtmlArticle article();

    HtmlAside aside();

    HtmlAudio audio();

    HtmlB b();

    HtmlBase base();

    HtmlBdi bdi();

    HtmlBdo bdo();

    HtmlBlockquote blockquote();

    HtmlBody body();

    HtmlBr br();

    HtmlButton button();

    HtmlCanvas canvas();

    HtmlCaption caption();

    HtmlCite cite();

    HtmlCode code();

    HtmlCol col();

    HtmlColgroup colgroup();

    HtmlData data();

    HtmlDatalist datalist();

    HtmlDd dd();

    HtmlDel del();

    HtmlDetails details();

    HtmlDfn dfn();

    HtmlDialog dialog();

    HtmlDiv div();

    HtmlDl dl();

    HtmlDt dt();

    HtmlEm em();

    HtmlEmbed embed();

    HtmlFieldset fieldset();

    HtmlFigcaption figcaption();

    HtmlFigure figure();

    HtmlFooter footer();

    HtmlForm form();

    HtmlH1 h1();

    HtmlH2 h2();

    HtmlH3 h3();

    HtmlH4 h4();

    HtmlH5 h5();

    HtmlH6 h6();

    HtmlHead head();

    HtmlHeader header();

    HtmlHr hr();

    HtmlHtml html();

    HtmlI i();

    HtmlIframe iframe();

    HtmlImg img();

    HtmlInput input();

    HtmlIns ins();

    HtmlKbd kbd();

    HtmlKeygen keygen();

    HtmlLabel label();

    HtmlLegend legend();

    HtmlLi li();

    HtmlLink link();

    HtmlMain main();

    HtmlMap map();

    HtmlMark mark();

    HtmlMeta meta();

    HtmlMeter meter();

    HtmlNav nav();

    HtmlNoscript noscript();

    HtmlObject object();

    HtmlOl ol();

    HtmlOptgroup optgroup();

    HtmlOption option();

    HtmlOutput output();

    HtmlP p();

    HtmlParam param();

    HtmlPre pre();

    HtmlProgress progress();

    HtmlQ q();

    HtmlRb rb();

    HtmlRp rp();

    HtmlRt rt();

    HtmlRtc rtc();

    HtmlRuby ruby();

    HtmlS s();

    HtmlSamp samp();

    HtmlScript script();

    HtmlSection section();

    HtmlSelect select();

    HtmlSmall small();

    HtmlSource source();

    HtmlSpan span();

    HtmlStrong strong();

    HtmlStyle style();

    HtmlSubandsup subandsup();

    HtmlSummary summary();

    HtmlTable table();

    HtmlTbody tbody();

    HtmlTd td();

    HtmlTemplate template();

    HtmlTextarea textarea();

    HtmlTfoot tfoot();

    HtmlTh th();

    HtmlThead thead();

    HtmlTime time();

    HtmlTitle title();

    HtmlTr tr();

    HtmlTrack track();

    HtmlU u();

    HtmlUl ul();

    HtmlVar var();

    HtmlVideo video();

    HtmlWbr wbr();

}
