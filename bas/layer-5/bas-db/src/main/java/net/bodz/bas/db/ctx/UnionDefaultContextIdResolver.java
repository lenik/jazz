package net.bodz.bas.db.ctx;

import java.util.*;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.order.PriorityComparator;

@ExcludedFromIndex
public class UnionDefaultContextIdResolver
        implements
            IDefaultContextIdsResolver {

    private List<IDefaultContextIdsResolver> resolvers;
    private int maxLevel;

    public UnionDefaultContextIdResolver() {
        reload();
    }

    public void reload() {
        resolvers = new ArrayList<>();
        for (IDefaultContextIdsResolver resolver : ServiceLoader.load(IDefaultContextIdsResolver.class))
            resolvers.add(resolver);
        Collections.sort(resolvers, PriorityComparator.INSTANCE);

        int maxOfAll = 0;
        for (IDefaultContextIdsResolver resolver : resolvers) {
            int maxLevel = resolver.getMaxLevel();
            if (maxLevel > maxOfAll)
                maxOfAll = maxLevel;
        }
        this.maxLevel = maxOfAll;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public Collection<String> resolveContextIds(int level) {
        Set<String> union = new LinkedHashSet<>();
        for (IDefaultContextIdsResolver resolver : resolvers) {
            Collection<String> set = resolver.resolveContextIds(level);
            if (set != null)
                union.addAll(set);
        }
        return union;
    }

    private static UnionDefaultContextIdResolver instance;

    public static UnionDefaultContextIdResolver getInstance() {
        if (instance == null) {
            synchronized (UnionDefaultContextIdResolver.class) {
                if (instance == null) {
                    instance = new UnionDefaultContextIdResolver();
                }
            }
        }
        return instance;
    }

}
