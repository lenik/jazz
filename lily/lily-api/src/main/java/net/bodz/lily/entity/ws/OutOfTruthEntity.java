package net.bodz.lily.entity.ws;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.lily.concrete.CoObject;
import net.bodz.lily.concrete.StructRow;
import net.bodz.lily.entity.manager.ResolvedEntity;

public class OutOfTruthEntity {

    static final int ID_PATH = 1;
    static final int FIELD_PATH = 2;

    StructRow entity;
    String[] consumedTokens;
    int mode;

    public static OutOfTruthEntity byIdPath(StructRow entity, String... consumedTokens) {
        OutOfTruthEntity a = new OutOfTruthEntity();
        a.entity = entity;
        a.consumedTokens = consumedTokens;
        a.mode = ID_PATH;
        return a;
    }

    public static OutOfTruthEntity byFieldPath(StructRow entity, String... consumedTokens) {
        OutOfTruthEntity a = new OutOfTruthEntity();
        a.entity = entity;
        a.consumedTokens = consumedTokens;
        a.mode = FIELD_PATH;
        return a;
    }

    public ResolvedEntity toResolved() {
        ResolvedEntity r = new ResolvedEntity();
        r.consumedTokenCount = consumedTokens.length;
        r.entity = entity;
        r.idFieldStrings = consumedTokens;

        if (entity instanceof CoObject)
            r.id = ((CoObject) entity).id();

        if (mode == ID_PATH)
            try {
                r.parseId(r.idFieldStrings);
            } catch (ParseException e) {
                throw new UnexpectedException(e.getMessage(), e);
            }
        return r;
    }

}
