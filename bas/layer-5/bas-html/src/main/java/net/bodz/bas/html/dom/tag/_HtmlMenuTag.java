package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The menu element represents a list of commands. 
  */
@SuppressWarnings("unchecked")
class _HtmlMenuTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlMenuTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The type attribute is an enumerated attribute indicating the kind of menu being declared. The attribute has two states. The popup keyword maps to the <dfn data-anolis-xref="popup menu state" id="popup-menu-state">popup menu state, in which the element is declaring a context menu or the menu for a menu button. The toolbar keyword maps to the <dfn data-anolis-xref="toolbar state" id="toolbar-state">toolbar state, in which the element is declaring a toolbar. The attribute may also be omitted. The missing value default is the popup menu state if the parent element is a menu element whose type attribute is in the popup menu state; otherwise, it is the toolbar state. 
      */
    public self_t type(Object val) {
        attr("type", val);
        return (self_t) this;
    }

    /**
      * The label attribute gives the label of the menu. It is used by user agents to display nested menus in the UI: a context menu containing another menu would use the nested menu's label attribute for the submenu's menu label. The label attribute must only be specified on menu elements whose parent element is a menu element whose type attribute is in the popup menu state. 
      */
    public self_t label(Object val) {
        attr("label", val);
        return (self_t) this;
    }

}
