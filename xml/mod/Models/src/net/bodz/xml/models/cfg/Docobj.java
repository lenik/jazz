package net.bodz.xml.models.cfg;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.xml.util.Term;
import net.bodz.xml.util.TermsParser;

public class Docobj {

    protected String id;
    protected String name;
    protected String qname;
    protected String opts;
    protected String cat;
    protected String disp;
    protected String icon;
    protected String info;

    protected int    flags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        // if (this.id != null)
        // Docobjs.unset(this.id);
        // Docobjs.set(id, this);
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
                    throw new IllegalArgumentException("Unknown option: " + term);
            }
        }
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

    private static final int COMMENT_OUT = 0x80000000;

    protected boolean parseOpts(Term opt) throws ParseException {
        String abb = opt.getName();
        int len = abb.length();
        switch (abb.charAt(0)) {
        case 'c':
            if (len == 1)
                flags |= COMMENT_OUT;
            else
                break;
            return true;
        }
        return false;
    }

    public boolean isCommentOut() {
        return 0 != (COMMENT_OUT & flags);
    }

}