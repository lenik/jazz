package net.bodz.bas.db.ctx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.order.PriorityComparator;

@ExcludedFromIndex
public class UnionDefaultContextIdsResolver
        implements IDefaultContextIdsResolver {

    private final List<IDefaultContextIdsResolver> resolvers = new ArrayList<>();
    private int maxLevel;

    public UnionDefaultContextIdsResolver() {
        reload();
    }

    public void reload() {
        resolvers.clear();
        for (IDefaultContextIdsResolver resolver : ServiceLoader.load(IDefaultContextIdsResolver.class))
            resolvers.add(resolver);
        resolvers.sort(PriorityComparator.INSTANCE);

        int maxOfAll = 0;
        for (IDefaultContextIdsResolver resolver : resolvers) {
            int maxLevel = resolver.getMaxLevel();
            if (maxLevel > maxOfAll)
                maxOfAll = maxLevel;
        }
        this.maxLevel = maxOfAll;
    }

    public List<IDefaultContextIdsResolver> getResolvers() {
        return resolvers;
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

    private static final UnionDefaultContextIdsResolver instance = new UnionDefaultContextIdsResolver();

    public static UnionDefaultContextIdsResolver getInstance() {
        return instance;
    }

}
