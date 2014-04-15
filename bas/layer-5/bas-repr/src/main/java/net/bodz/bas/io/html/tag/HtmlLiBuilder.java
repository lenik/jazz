package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * An li element's end tag may be omitted if the li element is immediately followed by another li element or if there is no more content in the parent element. Allowed ARIA role attribute values: listitem role (default - do not set), menuitem, menuitemcheckbox, menuitemradio, option, tab, treeitem or presentation. Allowed ARIA state and property attributes: Global aria-* attributes Any aria-* attributes applicable to the allowed roles. DOM interface:<!--TOPIC:DOM APIs--> interface HTMLLIElement : HTMLElement { attribute long value; }; <!--TOPIC:HTML-->The li element represents a list item. If its parent element is an ol, or ul, then the element is an item of the parent element's list, as defined for those elements. Otherwise, the list item has no defined list-related relationship to any other li element. 
  */
public class HtmlLiBuilder
        extends DecoratedHtmlTagBuilder<HtmlLiBuilder> {

    public HtmlLiBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
