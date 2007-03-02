package net.bodz.xml.xmod.util;

import net.bodz.xml.util.Term;
import net.bodz.xml.util.TermsParser;
import net.sf.freejava.err.ParseException;

public class Docobj {

    private String   id;
    protected String name;
    protected String qname;
    private String   opts;
    protected String cat;
    protected String disp;
    protected String icon;
    protected String info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (this.id != null)
            Docobjs.unset(this.id);
        Docobjs.set(id, this);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpts() {
        return opts;
    }

    public final void setOpts(String opts) throws ParseException {
        this.opts = opts;
        if (opts != null) {
            Term[] terms = TermsParser.parse(opts);
            for (Term term : terms) {
                if (!parseOpts(term))
                    throw new IllegalArgumentException("Unknown option: "
                            + term);
            }
        }
    }

    protected boolean parseOpts(Term opt) throws ParseException {
        return false;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
