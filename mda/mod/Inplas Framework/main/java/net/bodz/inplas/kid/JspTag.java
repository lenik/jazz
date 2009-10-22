package net.bodz.inplas.kid;

import net.sf.freejava.fp.xml.XMLPIX;


public class JspTag extends XMLPIX {

    public JspTag(Object content) {
        super("% ", " %", content);
    }

}
