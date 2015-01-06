package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The menuitem element is not rendered except as part of a popup menu. 
  */
@SuppressWarnings("unchecked")
class _HtmlMenuitemTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlMenuitemTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The type attribute indicates the kind of command: either a normal command with an associated action, or a state or option that can be toggled, or a selection of one item from a list of items. 
      */
    public self_t type(Object val) {
        attr("type", val);
        return (self_t) this;
    }

    /**
      * The element represents a normal command with an associated action. The Checkbox state The element represents a state or option that can be toggled. The Radio state The element represents a selection of one item from a list of items. The label attribute gives the name of the command, as shown to the user. The label attribute must be specified if the element is in the explicit command mode. If the attribute is specified, it must have a value that is not the empty string. 
      */
    public self_t label(Object val) {
        attr("label", val);
        return (self_t) this;
    }

    /**
      * The icon attribute gives a picture that represents the command. If the attribute is specified, the attribute's value must contain a valid non-empty URL potentially surrounded by spaces. To obtain the absolute URL of the icon when the attribute's value is not the empty string, the attribute's value must be resolved relative to the element. When the attribute is absent, or its value is the empty string, or <a data-anolis-xref="resolve a url" href="infrastructure.html#resolve-a-url">resolving its value fails, there is no icon. <!-- this is affected by the 
      */
    public self_t icon(Object val) {
        attr("icon", val);
        return (self_t) this;
    }

    /**
      * The disabled attribute is a boolean attribute that, if present, indicates that the command is not available in the current state. 
      */
    public self_t disabled(Object val) {
        attr("disabled", val);
        return (self_t) this;
    }

    /**
      * The checked attribute is a boolean attribute that, if present, indicates that the command is selected. The attribute must be omitted unless the type attribute is in either the Checkbox state or the Radio state. 
      */
    public self_t checked(Object val) {
        attr("checked", val);
        return (self_t) this;
    }

    /**
      * The radiogroup attribute gives the name of the group of commands that will be toggled when the command itself is toggled, for commands whose type attribute has the value "radio". The scope of the name is the child list of the parent element. The attribute must be omitted unless the type attribute is in the Radio state. When specified, the attribute's value must be a non-empty string. 
      */
    public self_t radiogroup(Object val) {
        attr("radiogroup", val);
        return (self_t) this;
    }

    /**
      * The default attribute indicates, if present, that the command is the one that would have been invoked if the user had directly activated the menu's subject instead of using the menu. The default attribute is a boolean attribute. 
      */
    public self_t default_(Object val) {
        attr("default", val);
        return (self_t) this;
    }

    public self_t command(Object val) {
        attr("command", val);
        return (self_t) this;
    }

}
