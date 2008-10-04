package net.bodz.xml.xmod.modcfg;

import java.util.ArrayList;
import java.util.List;

import net.bodz.xml.xmod.util.Docobj;

public class Setting extends Docobj {

    protected Modcfg          modcfg;
    protected String          value = "true";
    protected List<Condition> conditions;

    public Setting() {
        conditions = new ArrayList<Condition>();
    }

    protected void pre_set(Object outer) {
        assert outer instanceof Modcfg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
