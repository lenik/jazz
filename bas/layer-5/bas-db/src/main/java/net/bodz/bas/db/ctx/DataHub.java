package net.bodz.bas.db.ctx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.c.autowire.ProjectList;
import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.order.PriorityComparator;

@ExcludedFromIndex
public class DataHub
        implements
            IDataContextProvider {

    private List<IDataContextProvider> providers;

    public DataHub() {
        loadProviders();
    }

    synchronized void loadProviders() {
        if (providers == null) {
            providers = new ArrayList<>();
            for (IDataContextProvider provider : ServiceLoader.load(IDataContextProvider.class))
                providers.add(provider);
            Collections.sort(providers, PriorityComparator.INSTANCE);
        }
    }

    @Override
    public ConnectOptions getConnectOptions(String key) {
        for (IDataContextProvider provider : providers) {
            ConnectOptions options = provider.getConnectOptions(key);
            if (options != null)
                return options;
        }
        return null;
    }

    @Override
    public DataContext getDataContext(String key) {
        for (IDataContextProvider provider : providers) {
            DataContext dataContext = provider.getDataContext(key);
            if (dataContext != null)
                return dataContext;
        }
        return null;
    }

    String projectName = ProjectList.INSTANCE.topLevelName();

    public DataContext getMain() {
        String projectMain = K_MAIN + "." + projectName;

        DataContext dataContext = getDataContext(projectMain);
        if (dataContext != null)
            return dataContext;

        ConnectOptions options = requireConnectOptions(K_MAIN).clone();
        options.setDatabase(projectName);
        return new DataContext(options);
    }

    public DataContext getTest() {
        String projectMain = K_MAIN + "." + projectName;

        DataContext dataContext = getDataContext(projectMain, K_TEST);
        if (dataContext != null)
            return dataContext;

        return getMain();
    }

    public ConnectOptions getTemplate() {
        return requireConnectOptions(//
                K_TEMPLATE + "." + projectName, //
                K_TEMPLATE);
    }

    static DataHub instance = new DataHub();

    public static DataHub getPreferredHub() {
        return instance;
    }

}
