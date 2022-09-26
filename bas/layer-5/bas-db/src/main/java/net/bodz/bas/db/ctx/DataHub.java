package net.bodz.bas.db.ctx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.order.PriorityComparator;

@ExcludedFromIndex
public class DataHub
        implements
            IDataContextProvider {

    static final Logger logger = LoggerFactory.getLogger(DataHub.class);

    public static final String K_MAIN = "main";
    public static final String K_TEST = "test";
    public static final String K_TEMPLATE = "template";

    private List<IDataContextProvider> dataContextProviders;
    IDefaultContextIdsResolver defaultContextIdsResolver;

    public DataHub() {
        reload();
    }

    public synchronized void reload() {
        dataContextProviders = new ArrayList<>();
        for (IDataContextProvider provider : ServiceLoader.load(IDataContextProvider.class))
            dataContextProviders.add(provider);
        Collections.sort(dataContextProviders, PriorityComparator.INSTANCE);

        defaultContextIdsResolver = UnionDefaultContextIdResolver.getInstance();
    }

    @Override
    public ConnectOptions getConnectOptions(String key) {
        for (IDataContextProvider provider : dataContextProviders) {
            ConnectOptions options = provider.getConnectOptions(key);
            if (options != null)
                return options;
        }
        return null;
    }

    @Override
    public DataContext getDataContext(String key) {
        for (IDataContextProvider provider : dataContextProviders) {
            DataContext dataContext = provider.getDataContext(key);
            if (dataContext != null)
                return dataContext;
        }
        return null;
    }

    public DataContext findDataContextForScope(String scope) {
        Collection<String> contextIds = defaultContextIdsResolver.resolveContextIds(0);
        for (String contextId : contextIds) {
            String qName = contextId + "/" + scope;
            DataContext dataContext = getDataContext(qName);
            if (dataContext != null)
                return dataContext;
        }
        return null;
    }

    public ConnectOptions findConnectOptionsForScope(String scope) {
        Collection<String> contextIds = defaultContextIdsResolver.resolveContextIds(0);
        for (String contextId : contextIds) {
            String qName = contextId + "/" + scope;
            ConnectOptions connectOptions = getConnectOptions(qName);
            if (connectOptions != null)
                return connectOptions;
        }
        return null;
    }

    public DataContext getMain() {
        return findDataContextForScope(K_MAIN);
    }

    public DataContext getTest() {
        return findDataContextForScope(K_TEST);
    }

    public ConnectOptions getTemplate() {
        ConnectOptions template = findConnectOptionsForScope(K_TEMPLATE);
        if (template == null) {
            DataContext main = getMain();
            if (main == null)
                return null;
            template = main.getOptions();
        }
        return template;
    }

    static DataHub instance = new DataHub();

    public static DataHub getPreferredHub() {
        return instance;
    }

}
