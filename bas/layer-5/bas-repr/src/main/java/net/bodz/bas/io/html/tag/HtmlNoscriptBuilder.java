package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The noscript element is a blunt instrument. Sometimes, scripts might be enabled, but for some reason the page's script might fail. For this reason, it's generally better to avoid using noscript, and to instead design the script to change the page from being a scriptless page to a scripted page on the fly, as in the next example: 
  */
public class HtmlNoscriptBuilder
        extends DecoratedHtmlTagBuilder<HtmlNoscriptBuilder> {

    public HtmlNoscriptBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
