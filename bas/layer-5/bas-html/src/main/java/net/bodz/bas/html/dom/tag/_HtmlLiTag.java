package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * An li element's end tag may be omitted if the li element is immediately followed by another li element or if there is no more content in the parent element. Allowed ARIA role attribute values: listitem role (default - do not set), menuitem, menuitemcheckbox, menuitemradio, option, tab, treeitem or presentation. Allowed ARIA state and property attributes: Global aria-* attributes Any aria-* attributes applicable to the allowed roles. DOM interface:<!--TOPIC:DOM APIs--> interface HTMLLIElement : HTMLElement { attribute long value; }; <!--TOPIC:HTML-->The li element represents a list item. If its parent element is an ol, or ul, then the element is an item of the parent element's list, as defined for those elements. Otherwise, the list item has no defined list-related relationship to any other li element. 
  */
public class _HtmlLiTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    protected _HtmlLiTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
