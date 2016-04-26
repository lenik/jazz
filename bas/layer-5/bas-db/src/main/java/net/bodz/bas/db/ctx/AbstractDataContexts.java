package net.bodz.bas.db.ctx;

import java.io.IOException;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;

import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public abstract class AbstractDataContexts<K>
        implements IDataContexts<K> {

    static final Logger logger = LoggerFactory.getLogger(AbstractDataContexts.class);

    private Map<Object, DataContext> contexts = new IdentityHashMap<>();

    @Override
    public synchronized DataContext get(K key) {
        if (key == null)
            throw new NullPointerException("key");
        DataContext context = contexts.get(key);
        if (context == null) {
            ConnectOptions opts = getConnectOptions(key);
            if (opts == null)
                return null;
            context = new DataContext(opts);
            contexts.put(key, context);
        }
        return context;
    }

    protected abstract ConnectOptions getConnectOptions(K key);

    @Override
    public void remove(K key) {
        contexts.remove(key);
    }

    @Override
    public void removeAll() {
        contexts.clear();
    }

    @Override
    public void purge(K key) {
        DataContext context = contexts.remove(key);
        if (context != null)
            try {
                context.close();
            } catch (IOException e) {
                logger.error("Can't close data environ: " + e.getMessage(), e);
            }
    }

    @Override
    public void purgeAll() {
        Iterator<DataContext> iterator = contexts.values().iterator();
        while (iterator.hasNext()) {
            DataContext item = iterator.next();
            try {
                item.close();
            } catch (IOException e) {
                logger.error("Can't close: " + e.getMessage(), e);
            }
            iterator.remove();
        }
    }

}
