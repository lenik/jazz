package net.bodz.lily.model.base;

import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.manager.IEntityCommand;
import net.bodz.lily.entity.type.IEntityTypeInfo;

@IndexedType(includeAbstract = false)
public interface IEntityManager
        extends
            IPathDispatchable,
            IDataContextAware {

    Class<?> getEntityType();

    IEntityTypeInfo getEntityTypeInfo();

    IEntityCommand getCommand(String name);

    IEntityCommand getContentCommand(String name);

    IPathArrival dispatchToEntity(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException;

}
