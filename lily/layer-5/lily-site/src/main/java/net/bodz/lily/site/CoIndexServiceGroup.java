package net.bodz.lily.site;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.loader.ClassLoaders;
import net.bodz.bas.c.type.ClassNameComparator;
import net.bodz.bas.c.type.TypeIndex;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.html.io.BHtmlOut;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlUl;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoIndex;

public class CoIndexServiceGroup
        implements INamingGroup<CoIndex<?, ?>>, IPathDispatchable {

    static final Logger logger = LoggerFactory.getLogger(CoIndexServiceGroup.class);

    Map<String, CoIndex<?, ?>> map = new HashMap<>();

    @SuppressWarnings("rawtypes")
    public CoIndexServiceGroup(DataContext dataContext) {
        ClassLoader classLoader = ClassLoaders.getRuntimeClassLoader();
        TypeIndex typeIndex = TypeIndex.getInstance(classLoader);
        try {
            for (Class<? extends CoIndex> indexClass : typeIndex.list(CoIndex.class, false)) {
                String name = indexClass.getSimpleName();
                if (name.endsWith("Index"))
                    name = name.substring(0, name.length() - 5);
                // name = Strings.lcfirst(name);

                CoIndex<?, ?> existing = map.get(name);
                if (existing != null) {
                    logger.errorf("CoIndex name conflicts: %s and %s", existing.getClass(), indexClass);
                    logger.logf("CoIndex skipped: %s", indexClass);
                    continue;
                }
                CoIndex index;
                try {
                    index = indexClass.newInstance();
                } catch (ReflectiveOperationException e) {
                    logger.error(e, "Failed to instantiate " + indexClass);
                    continue;
                }
                index.setDataContext(dataContext);
                map.put(name, index);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to list CoIndex: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, ? extends CoIndex<?, ?>> getNameMap() {
        return map;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;
        switch (token) {
        case "_q":
            IHtmlOut out = new BHtmlOut();
            out.p().text("Available entities: ");
            List<Class<?>> etypes = new ArrayList<>(map.size());
            for (String k : map.keySet())
                etypes.add(map.get(k).getObjectType());
            Collections.sort(etypes, ClassNameComparator.getInstance());

            HtmlUl ul = out.ul();
            for (Class<?> etype : etypes) {
                String name = etype.getSimpleName();
                String label = etype.getName();
                ul.li().a().href("../" + name + "/__data__?columns=*").text(label);
            }
            ul.end();
            target = out;
            break;

        default:
            target = map.get(token);
        }

        if (target != null)
            return PathArrival.shift(previous, this, target, tokens);
        return null;
    }

}
