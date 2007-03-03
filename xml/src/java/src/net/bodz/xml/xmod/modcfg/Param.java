package net.bodz.xml.xmod.modcfg;

import net.bodz.xml.xmod.util.Docobj;

public class Param extends Docobj {

    protected Modcfg modcfg;
    protected String value = "true";

    protected void pre_set(Object outer) {
        assert outer instanceof Modcfg;
        modcfg = (Modcfg) outer;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
