package net.bodz.lily.tool.wsdoc;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class EntityIndex
        implements IPathDispatchable {

    ModuleIndexer indexer;

    public EntityIndex(ModuleIndexer indexer) {
        if (indexer == null)
            throw new NullPointerException("indexer");
        this.indexer = indexer;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = indexer.getEntity(token);
        if (target == null)
            return null;
        return PathArrival.shift(previous, this, target, tokens);
    }

}
