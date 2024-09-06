package net.bodz.bas.db.ctx;

import java.util.Collection;
import java.util.List;

public class DefaultContextIds {

    static final UnionDefaultContextIdsResolver resolver //
            = UnionDefaultContextIdsResolver.getInstance();

    public static int getMaxLevel() {
        return resolver.getMaxLevel();
    }

    public static Collection<String> resolve() {
        return resolve(0);
    }

    public static Collection<String> resolve(int level) {
        return resolver.resolveContextIds(level);
    }

    public static List<IDefaultContextIdsResolver> getResolvers() {
        return resolver.getResolvers();
    }

}
