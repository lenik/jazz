package net.bodz.bas.io.html;

import net.bodz.bas.io.xml.IXmlTagBuilder;
import net.bodz.bas.ui.css3.property.DirectionMode;

/**
 * See: <a href="http://www.w3.org/TR/html5/dom.html#global-attributes">HTML5 Global Attributes</a>
 */
public interface IHtmlTagBuilder<self_t extends IXmlTagBuilder>
        extends IXmlTagBuilder {

    /**
     * The id attribute specifies its element's unique identifier (ID). [DOM]
     * <p>
     * The value must be unique amongst all the IDs in the element's home subtree and must contain
     * at least one character. The value must not contain any space characters.
     */
    @Override
    self_t id(String id);

    @Override
    self_t attr(String name, Object value);

    @Override
    self_t text(String text);

    @Override
    self_t text(Object content);

    // HTML5 Global Attributes

    /**
     * All HTML elements may have the accesskey content attribute set. The accesskey attribute's
     * value is used by the user agent as a guide for creating a keyboard shortcut that activates or
     * focuses the element.
     * <p>
     * If specified, the value must be an ordered set of unique space-separated tokens that are
     * case-sensitive, each of which must be exactly one Unicode code point in length.
     */
    self_t accesskey(String accesskey);

    /**
     * Every HTML element may have a class attribute specified.
     * <p>
     * The attribute, if specified, must have a value that is a set of space-separated tokens
     * representing the various classes that the element belongs to.
     * <p>
     * The classes that an HTML element has assigned to it consists of all the classes returned when
     * the value of the class attribute is split on spaces. (Duplicates are ignored.)
     */
    self_t class_(String class_);

    /**
     * The contenteditable attribute is an enumerated attribute whose keywords are the empty string,
     * true, and false. The empty string and the true keyword map to the true state. The false
     * keyword maps to the false state. In addition, there is a third state, the inherit state,
     * which is the missing value default (and the invalid value default).
     */
    self_t contenteditable(Boolean contenteditable);

    /**
     * The dir attribute specifies the element's text directionality. The attribute is an enumerated
     * attribute with the following keywords and states:
     */
    self_t dir(DirectionMode dir);

    /**
     * All HTML elements may have the draggable content attribute set. The draggable attribute is an
     * enumerated attribute. It has three states. The first state is true and it has the keyword
     * true. The second state is false and it has the keyword false. The third state is auto; it has
     * no keywords but it is the missing value default.
     */
    self_t draggable(Boolean draggable);

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
    self_t dropzone(String dropzone);

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
    self_t hidden(Boolean hidden);

    /**
     * The lang attribute (in no namespace) specifies the primary language for the element's
     * contents and for any of the element's attributes that contain text. Its value must be a valid
     * BCP 47 language tag, or the empty string. Setting the attribute to the empty string indicates
     * that the primary language is unknown. [BCP47]
     */
    self_t lang(String lang);

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
    self_t spellcheck(String spellcheck);

    /**
     * All HTML elements may have the style content attribute set. This is a CSS styling attribute
     * as defined by the CSS Styling Attribute Syntax specification. [CSSATTR]
     */
    self_t style(String style);

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
    self_t tabindex(Integer tabindex);

    /**
     * The title attribute represents advisory information for the element, such as would be
     * appropriate for a tooltip. On a link, this could be the title or a description of the target
     * resource; on an image, it could be the image credit or a description of the image; on a
     * paragraph, it could be a footnote or commentary on the text; on a citation, it could be
     * further information about the source; on interactive content, it could be a label for, or
     * instructions for, use of the element; and so forth. The value is text.
     */
    self_t title(String title);

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
    self_t translate(Boolean translate);

    // Events

    self_t onblur(String handler);

    self_t oncancel(String handler);

    self_t oncanplay(String handler);

    self_t oncanplaythrough(String handler);

    self_t onchange(String handler);

    self_t onclick(String handler);

    self_t onclose(String handler);

    self_t oncuechange(String handler);

    self_t ondblclick(String handler);

    self_t ondrag(String handler);

    self_t ondragend(String handler);

    self_t ondragenter(String handler);

    self_t ondragexit(String handler);

    self_t ondragleave(String handler);

    self_t ondragover(String handler);

    self_t ondragstart(String handler);

    self_t ondrop(String handler);

    self_t ondurationchange(String handler);

    self_t onemptied(String handler);

    self_t onended(String handler);

    self_t onerror(String handler);

    self_t onfocus(String handler);

    self_t oninput(String handler);

    self_t oninvalid(String handler);

    self_t onkeydown(String handler);

    self_t onkeypress(String handler);

    self_t onkeyup(String handler);

    self_t onload(String handler);

    self_t onloadeddata(String handler);

    self_t onloadedmetadata(String handler);

    self_t onloadstart(String handler);

    self_t onmousedown(String handler);

    self_t onmouseenter(String handler);

    self_t onmouseleave(String handler);

    self_t onmousemove(String handler);

    self_t onmouseout(String handler);

    self_t onmouseover(String handler);

    self_t onmouseup(String handler);

    self_t onmousewheel(String handler);

    self_t onpause(String handler);

    self_t onplay(String handler);

    self_t onplaying(String handler);

    self_t onprogress(String handler);

    self_t onratechange(String handler);

    self_t onreset(String handler);

    self_t onresize(String handler);

    self_t onscroll(String handler);

    self_t onseeked(String handler);

    self_t onseeking(String handler);

    self_t onselect(String handler);

    self_t onshow(String handler);

    self_t onstalled(String handler);

    self_t onsubmit(String handler);

    self_t onsuspend(String handler);

    self_t ontimeupdate(String handler);

    self_t ontoggle(String handler);

    self_t onvolumechange(String handler);

    self_t onwaiting(String handler);

}
