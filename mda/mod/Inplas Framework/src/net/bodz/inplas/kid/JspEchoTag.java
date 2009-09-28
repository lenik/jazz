package net.bodz.inplas.kid;

import net.sf.freejava.fp.xml.XMLPIX;


public class JspEchoTag extends XMLPIX {

    public JspEchoTag(Object content) {
        super("%@ ", " %", content);
    }

}
