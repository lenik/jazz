package net.bodz.lily.site.test.web;

import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class MapperService
        implements
            IPathDispatchable {

    DataContext dataContext;
    Map<String, Class<?>> map;

    public MapperService(DataContext dataContext) {
        this.dataContext = dataContext;

        map = new LinkedHashMap<>();
        for (Class<?> mapper : IndexedTypes.list(IMapper.class, true)) {
            map.put(mapper.getSimpleName(), mapper);
        }
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        Class<?> mapperClass = map.get(token + "Mapper");
        Object mapper = dataContext.getMapper(mapperClass);

        return PathArrival.shift(previous, this, mapper, tokens);
    }

}
