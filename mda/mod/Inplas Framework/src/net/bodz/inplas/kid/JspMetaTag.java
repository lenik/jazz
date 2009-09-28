package net.bodz.inplas.kid;

import net.sf.freejava.fp.xml.XMLPIX;


public class JspMetaTag extends XMLPIX {

    public JspMetaTag(Object content) {
        super("%= ", " %", content);
    }

}
