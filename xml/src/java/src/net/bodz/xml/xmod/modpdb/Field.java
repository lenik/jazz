package net.bodz.xml.xmod.modpdb;

import java.util.List;

import net.bodz.xml.util.Term;
import net.sf.freejava.fp.dump.XMLDump;

public class Field extends Docobj {

    /** N */
    public static final int NULLABLE = 1;

    protected int           flags;

    // TODO - draft {
    protected List<Field>   pk;
    protected List<KeySet>  fkSet;
    protected Field         f_TimeStamp;
    protected Field         f_Version;
    protected String        defValue;
    protected String        defExp;
    protected String        identityMethod;
    // }
    
    @XMLDump
    protected String        type;

    /**
     * <pre>
     *   N=nullable
     *   K=primary key (group)
     *   F([nam,] n:m ? table.field[, disp-sql]) = foreign key
     *       F-&gt; normal ref-integrity
     *       F--&gt; no ref-integrity
     *       F=&gt; cascade-update
     *       F&gt;&gt; cascade-delete
     *   I(nam)=index (group)
     *   Ic(nam)=clustered index
     *   U(nam)=unique (group)
     *   T(field, ?)=timestamp
     *   V(field, ?)=version
     *   S?=special mode
     *       Sd=cache/display-name
     *   D(value)=default value
     *   Dx(sql-exp)=default exp
     *   Di(gen-method) = identity
     * </pre>
     */
    @Override
    protected boolean parseOpts(Term opt) {
        return super.parseOpts(opt);
    }
}
