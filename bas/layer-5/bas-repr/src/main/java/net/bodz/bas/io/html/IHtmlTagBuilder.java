package net.bodz.bas.io.html;

import net.bodz.bas.io.xml.IXmlTagBuilder;

public interface IHtmlTagBuilder<self_t extends IXmlTagBuilder>
        extends IXmlTagBuilder {

    @Override
    self_t id(String id);

    @Override
    self_t attr(String name, Object value);

    self_t styleClass(String class_);

    self_t style(String style);

    // self_t onclick(String script);

}
