package net.bodz.xml.xmod.modpdb;

import net.bodz.xml.util.Term;
import net.bodz.xml.xmod.util.Docobj;
import net.sf.freejava.err.ParseException;
import net.sf.freejava.fp.dump.XMLDump;

public class View extends Docobj {

    protected Modpdb db;

    @XMLDump
    protected Yasql  src;

    @XMLDump
    protected int    flags;

    public View() {
    }

    protected void pre_set(Object outer) {
        assert outer instanceof Modpdb;
        db = (Modpdb) outer;
    }

    /**
     * <pre>
     * D = cache / dict
     * </pre>
     */
    @Override
    protected boolean parseOpts(Term opt) throws ParseException {
        String abb = opt.getName();
        int len = abb.length();
        switch (abb.charAt(0)) {
        case 'D':
            if (len == 1)
                flags |= Table.CACHED;
            else if ("Dr".equals(abb))
                flags |= Table.CACHED | Table.NOT_REFERENCED;
            else
                break;
            return true;
        case 'R':
            if ("Ro".equals(abb))
                flags |= Table.READ_ONLY;
            else
                break;
            return true;
        case 'T':
            if (len == 1)
                flags |= Table.TRANSIENT;
            else
                break;
            return true;
        }
        return super.parseOpts(opt);
    }

}
