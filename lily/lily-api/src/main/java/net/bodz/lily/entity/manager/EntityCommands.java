package net.bodz.lily.entity.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class EntityCommands {

    static final List<IEntityCommandProvider> providers = loadProviders();

    static List<IEntityCommandProvider> loadProviders() {
        List<IEntityCommandProvider> providers = new ArrayList<>();
        for (IEntityCommandProvider provider : ServiceLoader.load(IEntityCommandProvider.class)) {
            providers.add(provider);
        }
        return providers;
    }

    public static List<IEntityCommandType> forEntityClass(Class<?> entityClass) {
        List<IEntityCommandType> list = new ArrayList<>();
        for (IEntityCommandProvider provider : providers) {
            List<IEntityCommandType> cmds = provider.getCommands(entityClass);
            list.addAll(cmds);
        }
        return list;
    }

}
