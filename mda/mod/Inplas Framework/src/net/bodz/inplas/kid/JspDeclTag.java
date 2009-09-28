package net.bodz.inplas.kid;

import net.sf.freejava.fp.xml.XMLPIX;


public class JspDeclTag extends XMLPIX {

    public JspDeclTag(Object content) {
        super("%! ", " %", content);
    }

}
