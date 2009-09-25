package net.bodz.xml.models.pdb;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.util.Objects;
import net.bodz.xml.a.XMLDump;
import net.bodz.xml.util.Term;
import net.bodz.xml.xmod.util.Docobj;

import org.jibx.runtime.IUnmarshallingContext;

public class Field extends Docobj {

    /** N */
    public static final int NULLABLE      = 1;

    /** Sd */
    public static final int CACHE_DISPLAY = 0x10000;

    protected Table         table;
    protected int           flags;

    protected String        defValue;
    protected String        defExp;
    protected String        identityMethod;

    @XMLDump
    protected String        type;

    public Field(Table table) {
        this.table = table;
    }

    protected Field() {
    }

    protected void pre_set(IUnmarshallingContext uctx) {
        Object outer = uctx.getStackObject(1);
        table = (Table) outer;
    }

    /**
     * <pre>
     *      N=nullable
     *      K=primary key (group)
     *      F([nam,] n:m ? table.field[, disp-sql]) = foreign key
     *          F-&gt; normal ref-integrity
     *          F--&gt; no ref-integrity
     *          F=&gt; cascade-update
     *          F&gt;&gt; cascade-delete
     *      I(nam)=index (group)
     *      Ic(nam)=clustered index
     *      U(nam)=unique (group)
     *      T(field, ?)=timestamp
     *      V(field, ?)=version
     *      S?=special mode
     *          Sd=cache/display-name
     *      D(value)=default value
     *      Dx(sql-exp)=default exp
     *      Di(gen-method) = identity
     * </pre>
     */
    @Override
    protected boolean parseOpts(Term opt) throws ParseException {
        String abb = opt.getName();
        int len = abb.length();
        switch (abb.charAt(0)) {
        case 'D':
            if (len == 1)
                defValue = opt.getParam(0);
            else if ("Dx".equals(abb))
                defExp = opt.getParam(0);
            else if ("Di".equals(abb))
                identityMethod = opt.getParam(0);
            else
                break;
            return true;
        case 'F':
            if (len == 1) {
                String nam = null;
                String cnt;
                String disp = null;
                int n = opt.getParamCount();
                if (n == 0)
                    throw new ParseException(
                            "F([nam,] n:m ? table.field[, disp-sql])");
                else if (n == 1)
                    cnt = opt.getParam(0);
                else {
                    nam = opt.getParam(0);
                    cnt = opt.getParam(1);
                    if (n > 2)
                        disp = opt.getParam(2);
                }
                String relstr = cnt.substring(0, 3);
                int rel = ForeignKey.parseRelation(relstr);
                if (rel == -1)
                    throw new ParseException("Invalid relation-string: "
                            + relstr);

                int behavPos = cnt.lastIndexOf('>');
                String behavstr = cnt.substring(3, behavPos + 1).trim();
                int behavUpdate = ForeignKey.parseUpdateBehav(behavstr);
                int behavDelete = ForeignKey.parseDeleteBehav(behavstr);
                if (behavUpdate == -1 || behavDelete == -1)
                    throw new ParseException("Invalid behav-string: "
                            + behavstr);

                String rtbl = cnt.substring(behavPos + 1).trim();
                Table rtable = table.db.getTable(rtbl);
                int fieldPos = rtbl.indexOf('.');
                String rfld = name;
                if (fieldPos != -1) {
                    rfld = rtbl.substring(fieldPos + 1);
                    rtbl = rtbl.substring(0, fieldPos);
                }

                ForeignKey fk = table.fkeys.get(nam);
                if (fk == null) {
                    fk = new ForeignKey(table, new String[] { name }, rtable,
                            new String[] { rfld });
                    fk.setUpdateBehav(behavUpdate);
                    fk.setDeleteBehav(behavDelete);
                    if (disp != null)
                        fk.disp = disp;
                    table.fkeys.put(nam, fk);
                } else {
                    fk = new ForeignKey(fk, name, rfld);
                }
            } else
                break;
            return true;
        case 'U':
        case 'I':
            if (len == 1 || "Ic".equals(abb)) {
                boolean uniq = abb.charAt(0) == 'U';
                boolean clustered = len != 1;
                String nam = null;
                if (opt.getParamCount() > 0)
                    nam = opt.getParam(0);
                Index idx = table.idxes.get(nam);
                if (idx == null) {
                    if (clustered)
                        idx = new ClusteredIndex(table, name);
                    else
                        idx = new Index(table, name);
                    idx.setUnique(uniq);
                    table.idxes.put(nam, idx);
                } else {
                    if (clustered)
                        idx = new ClusteredIndex(idx, name);
                    else
                        idx = new Index(idx, name);
                }
            } else
                break;
            return true;
        case 'K':
            if (len == 1) {
                if (table.pkey == null)
                    table.pkey = new PrimaryKey(table, name);
                else
                    table.pkey = new PrimaryKey(table.pkey, name);
            } else
                break;
            return true;
        case 'N':
            if (len == 1)
                flags |= NULLABLE;
            else
                break;
            return true;
        case 'S':
            if (len == 2) {
                switch (name.charAt(1)) {
                case 'd':
                    flags |= CACHE_DISPLAY;
                    return true;
                }
            }
            break;
        case 'T':
            if (len == 1)
                table.f_TimeStamp = this;
            else
                break;
            return true;
        case 'V':
            if (len == 1)
                table.f_Version = this;
            else
                break;
            return true;
        }
        return super.parseOpts(opt);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        assert obj != this;
        assert obj != null;
        assert obj instanceof Field;
        Field b = (Field) obj;
        if (flags != b.flags)
            return false;
        if (!Objects.equals(type, b.type))
            return false;
        // TODO - more...
        return true;
    }
}
