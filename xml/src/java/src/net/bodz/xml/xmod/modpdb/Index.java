package net.bodz.xml.xmod.modpdb;

import java.util.List;

import net.bodz.xml.util.Term;
import net.bodz.xml.xmod.util.Docobj;

public class Index extends Docobj {

    protected List<String> fields;

    /**
     * <pre>
     *   K=primary key
     *   F(n:m ? table.field)=foreign key
     *   I=index
     *   Ic=clustered index
     *   U=unique
     * </pre>
     */
    @Override
    protected boolean parseOpts(Term opt) {
        return super.parseOpts(opt);
    }

}
