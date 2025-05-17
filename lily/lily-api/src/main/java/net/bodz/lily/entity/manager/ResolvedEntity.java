package net.bodz.lily.entity.manager;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.pojo.Pair;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.concrete.CoObject;
import net.bodz.lily.concrete.StructRow;
import net.bodz.lily.entity.type.EntityTypes;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public class ResolvedEntity
        implements
            IPathDispatchable,
            IJsonForm {

    public int consumedTokenCount;

    public String idFieldStrings[];
    public Object idFields[];
    public Object id;

    public StructRow entity;

    public String preferredExtension;

    public static ResolvedEntity convert(IPathArrival a, boolean parseId) {
        ResolvedEntity r = new ResolvedEntity();
        String[] consumedTokens = a.getConsumedTokens();
        int n = consumedTokens.length;
        r.consumedTokenCount = n;
        r.idFieldStrings = consumedTokens;

        Object target = a.getTarget();
        if (target instanceof StructRow) {
            r.entity = (StructRow) target;

            if (target instanceof CoObject)
                r.id = ((CoObject) target).id();
            if (parseId)
                try {
                    r.parseId(consumedTokens);
                } catch (ParseException e) {
                    throw new UnexpectedException(e.getMessage(), e);
                }
        }
        return r;
    }

    public void parseId(String[] tokens)
            throws ParseException {
        IEntityTypeInfo typeInfo = EntityTypes.getTypeInfo(entity.getClass());
        IProperty[] pkProps = typeInfo.getPrimaryKeyProperties();
        if (pkProps.length == tokens.length) {
            Pair<Object, Object[]> id2;
            id2 = typeInfo.parseId2(tokens);
            id = id2.first;
            idFields = id2.second;
        }
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        if (entity instanceof IPathDispatchable) {
            IPathDispatchable pd = (IPathDispatchable) entity;
            return pd.dispatch(previous, tokens, q);
        }
        return null;
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        if (entity == null) {
            out.entry("error", "null entity.");
            return;
        }
        if (! (entity instanceof IJsonForm)) {
            out.entry("error", "can't convert to JSON: " + entity);
            return;
        }
        IJsonForm jsonEntity = entity;
        jsonEntity.jsonOut(out, opts);
    }

    @Override
    public String toString() {
        return String.format("%s: %s", id, entity);
    }

}
