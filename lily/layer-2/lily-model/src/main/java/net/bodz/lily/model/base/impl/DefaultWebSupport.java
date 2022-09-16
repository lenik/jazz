package net.bodz.lily.model.base.impl;

import java.io.IOException;

import org.apache.ibatis.exceptions.PersistenceException;

import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.db.ibatis.IMapperProvider;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.req.IMethodOfRequest;
import net.bodz.bas.repr.req.MethodNames;
import net.bodz.lily.model.base.CoObject;

public class DefaultWebSupport
        implements IWebSupport {

    CoObject object;

    public DefaultWebSupport(CoObject object) {
        this.object = object;
    }

    @Override
    public Object persist(IHtmlViewContext ctx, IHtmlOut out)
            throws PersistenceException, IOException {
        IMapperProvider provider = ctx.query(IMapperProvider.class);
        Class<IEntityMapper<CoObject, ?>> mapperClass = IMapper.fn.getMapperClass(getClass());
        IEntityMapper<CoObject, ?> mapper = provider.getMapper(mapperClass);
        if (mapper == null)
            throw new PersistenceException("No mapper for " + getClass());

        String methodName = ctx.query(IMethodOfRequest.class).getMethodName();

        boolean creation = false;
        if (object.id() == null)
            creation = true;
        else if (methodName != null)
            switch (methodName) {
            case MethodNames.CREATE:
                creation = true;
                break;
            case MethodNames.UPDATE:
                creation = false;
                break;
            }

        if (creation) {
            mapper.insert(object); // id will be filled.
        } else {
            mapper.update(object);
        }
        return object.id();
    }

}
