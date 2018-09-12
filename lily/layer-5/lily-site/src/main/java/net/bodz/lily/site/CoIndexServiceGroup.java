package net.bodz.lily.site;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.loader.ClassLoaders;
import net.bodz.bas.c.type.TypeIndex;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.lily.model.base.CoIndex;

public class CoIndexServiceGroup
        implements INamingGroup<CoIndex<?, ?>> {

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

}
