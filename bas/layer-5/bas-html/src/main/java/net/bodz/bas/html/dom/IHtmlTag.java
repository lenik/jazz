package net.bodz.bas.html.dom;

import net.bodz.bas.html.dom.tag.*;
import net.bodz.bas.ui.css3.property.DirectionMode;
import net.bodz.bas.xml.dom.IXmlTag;

/**
 * See: <a href="http://www.w3.org/TR/html5/dom.html#global-attributes">HTML5 Global Attributes</a>
 */
public interface IHtmlTag
        extends IXmlTag {

    /**
     * The id attribute specifies its element's unique identifier (ID). [DOM]
     * <p>
     * The value must be unique amongst all the IDs in the element's home subtree and must contain
     * at least one character. The value must not contain any space characters.
     */
    IHtmlTag id(String id);

    // HTML5 Global Attributes

    /**
     * All HTML elements may have the accesskey content attribute set. The accesskey attribute's
     * value is used by the user agent as a guide for creating a keyboard shortcut that activates or
     * focuses the element.
     * <p>
     * If specified, the value must be an ordered set of unique space-separated tokens that are
     * case-sensitive, each of which must be exactly one Unicode code point in length.
     */
    IHtmlTag accesskey(String accesskey);

    /**
     * Every HTML element may have a class attribute specified.
     * <p>
     * The attribute, if specified, must have a value that is a set of space-separated tokens
     * representing the various classes that the element belongs to.
     * <p>
     * The classes that an HTML element has assigned to it consists of all the classes returned when
     * the value of the class attribute is split on spaces. (Duplicates are ignored.)
     */
    IHtmlTag class_(String class_);

    /**
     * The contenteditable attribute is an enumerated attribute whose keywords are the empty string,
     * true, and false. The empty string and the true keyword map to the true state. The false
     * keyword maps to the false state. In addition, there is a third state, the inherit state,
     * which is the missing value default (and the invalid value default).
     */
    IHtmlTag contenteditable(Boolean contenteditable);

    /**
     * The dir attribute specifies the element's text directionality. The attribute is an enumerated
     * attribute with the following keywords and states:
     */
    IHtmlTag dir(DirectionMode dir);

    /**
     * All HTML elements may have the draggable content attribute set. The draggable attribute is an
     * enumerated attribute. It has three states. The first state is true and it has the keyword
     * true. The second state is false and it has the keyword false. The third state is auto; it has
     * no keywords but it is the missing value default.
     */
    IHtmlTag draggable(Boolean draggable);

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
    IHtmlTag dropzone(String dropzone);

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
    IHtmlTag hidden(Boolean hidden);

    /**
     * The lang attribute (in no namespace) specifies the primary language for the element's
     * contents and for any of the element's attributes that contain text. Its value must be a valid
     * BCP 47 language tag, or the empty string. Setting the attribute to the empty string indicates
     * that the primary language is unknown. [BCP47]
     */
    IHtmlTag lang(String lang);

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
    IHtmlTag spellcheck(String spellcheck);

    /**
     * All HTML elements may have the style content attribute set. This is a CSS styling attribute
     * as defined by the CSS Styling Attribute Syntax specification. [CSSATTR]
     */
    IHtmlTag style(String style);

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
    IHtmlTag tabindex(Integer tabindex);

    /**
     * The title attribute represents advisory information for the element, such as would be
     * appropriate for a tooltip. On a link, this could be the title or a description of the target
     * resource; on an image, it could be the image credit or a description of the image; on a
     * paragraph, it could be a footnote or commentary on the text; on a citation, it could be
     * further information about the source; on interactive content, it could be a label for, or
     * instructions for, use of the element; and so forth. The value is text.
     */
    IHtmlTag title(String title);

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
    IHtmlTag translate(Boolean translate);

    // Events

    IHtmlTag onblur(String handler);

    IHtmlTag oncancel(String handler);

    IHtmlTag oncanplay(String handler);

    IHtmlTag oncanplaythrough(String handler);

    IHtmlTag onchange(String handler);

    IHtmlTag onclick(String handler);

    IHtmlTag onclose(String handler);

    IHtmlTag oncuechange(String handler);

    IHtmlTag ondblclick(String handler);

    IHtmlTag ondrag(String handler);

    IHtmlTag ondragend(String handler);

    IHtmlTag ondragenter(String handler);

    IHtmlTag ondragexit(String handler);

    IHtmlTag ondragleave(String handler);

    IHtmlTag ondragover(String handler);

    IHtmlTag ondragstart(String handler);

    IHtmlTag ondrop(String handler);

    IHtmlTag ondurationchange(String handler);

    IHtmlTag onemptied(String handler);

    IHtmlTag onended(String handler);

    IHtmlTag onerror(String handler);

    IHtmlTag onfocus(String handler);

    IHtmlTag oninput(String handler);

    IHtmlTag oninvalid(String handler);

    IHtmlTag onkeydown(String handler);

    IHtmlTag onkeypress(String handler);

    IHtmlTag onkeyup(String handler);

    IHtmlTag onload(String handler);

    IHtmlTag onloadeddata(String handler);

    IHtmlTag onloadedmetadata(String handler);

    IHtmlTag onloadstart(String handler);

    IHtmlTag onmousedown(String handler);

    IHtmlTag onmouseenter(String handler);

    IHtmlTag onmouseleave(String handler);

    IHtmlTag onmousemove(String handler);

    IHtmlTag onmouseout(String handler);

    IHtmlTag onmouseover(String handler);

    IHtmlTag onmouseup(String handler);

    IHtmlTag onmousewheel(String handler);

    IHtmlTag onpause(String handler);

    IHtmlTag onplay(String handler);

    IHtmlTag onplaying(String handler);

    IHtmlTag onprogress(String handler);

    IHtmlTag onratechange(String handler);

    IHtmlTag onreset(String handler);

    IHtmlTag onresize(String handler);

    IHtmlTag onscroll(String handler);

    IHtmlTag onseeked(String handler);

    IHtmlTag onseeking(String handler);

    IHtmlTag onselect(String handler);

    IHtmlTag onshow(String handler);

    IHtmlTag onstalled(String handler);

    IHtmlTag onsubmit(String handler);

    IHtmlTag onsuspend(String handler);

    IHtmlTag ontimeupdate(String handler);

    IHtmlTag ontoggle(String handler);

    IHtmlTag onvolumechange(String handler);

    IHtmlTag onwaiting(String handler);

    // HTML Tags
    IHtmlTag insert(String tagName);

    HtmlATag a();

    HtmlAbbrTag abbr();

    HtmlAddressTag address();

    HtmlAreaTag area();

    HtmlArticleTag article();

    HtmlAsideTag aside();

    HtmlAudioTag audio();

    HtmlBTag b();

    HtmlBaseTag base();

    HtmlBdiTag bdi();

    HtmlBdoTag bdo();

    HtmlBlockquoteTag blockquote();

    HtmlBodyTag body();

    HtmlBrTag br();

    HtmlButtonTag button();

    HtmlCanvasTag canvas();

    HtmlCaptionTag caption();

    HtmlCiteTag cite();

    HtmlCodeTag code();

    HtmlColTag col();

    HtmlColgroupTag colgroup();

    HtmlDataTag data();

    HtmlDatalistTag datalist();

    HtmlDdTag dd();

    HtmlDelTag del();

    HtmlDetailsTag details();

    HtmlDfnTag dfn();

    HtmlDialogTag dialog();

    HtmlDivTag div();

    HtmlDlTag dl();

    HtmlDtTag dt();

    HtmlEmTag em();

    HtmlEmbedTag embed();

    HtmlFieldsetTag fieldset();

    HtmlFigcaptionTag figcaption();

    HtmlFigureTag figure();

    HtmlFooterTag footer();

    HtmlFormTag form();

    HtmlH1Tag h1();

    HtmlH2Tag h2();

    HtmlH3Tag h3();

    HtmlH4Tag h4();

    HtmlH5Tag h5();

    HtmlH6Tag h6();

    HtmlHeadTag head();

    HtmlHeaderTag header();

    HtmlHrTag hr();

    HtmlHtmlTag html();

    HtmlITag i();

    HtmlIframeTag iframe();

    HtmlImgTag img();

    HtmlInputTag input();

    HtmlInsTag ins();

    HtmlKbdTag kbd();

    HtmlKeygenTag keygen();

    HtmlLabelTag label();

    HtmlLegendTag legend();

    HtmlLiTag li();

    HtmlLinkTag link();

    HtmlMainTag main();

    HtmlMapTag map();

    HtmlMarkTag mark();

    HtmlMetaTag meta();

    HtmlMeterTag meter();

    HtmlNavTag nav();

    HtmlNoscriptTag noscript();

    HtmlObjectTag object();

    HtmlOlTag ol();

    HtmlOptgroupTag optgroup();

    HtmlOptionTag option();

    HtmlOutputTag output();

    HtmlPTag p();

    HtmlParamTag param();

    HtmlPreTag pre();

    HtmlProgressTag progress();

    HtmlQTag q();

    HtmlRbTag rb();

    HtmlRpTag rp();

    HtmlRtTag rt();

    HtmlRtcTag rtc();

    HtmlRubyTag ruby();

    HtmlSTag s();

    HtmlSampTag samp();

    HtmlScriptTag script();

    HtmlSectionTag section();

    HtmlSelectTag select();

    HtmlSmallTag small();

    HtmlSourceTag source();

    HtmlSpanTag span();

    HtmlStrongTag strong();

    HtmlStyleTag style();

    HtmlSubandsupTag subandsup();

    HtmlSummaryTag summary();

    HtmlTableTag table();

    HtmlTbodyTag tbody();

    HtmlTdTag td();

    HtmlTemplateTag template();

    HtmlTextareaTag textarea();

    HtmlTfootTag tfoot();

    HtmlThTag th();

    HtmlTheadTag thead();

    HtmlTimeTag time();

    HtmlTitleTag title();

    HtmlTrTag tr();

    HtmlTrackTag track();

    HtmlUTag u();

    HtmlUlTag ul();

    HtmlVarTag var();

    HtmlVideoTag video();

    HtmlWbrTag wbr();

}
