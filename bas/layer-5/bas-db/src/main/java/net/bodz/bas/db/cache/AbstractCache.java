package net.bodz.bas.db.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.l10n.en.English;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public abstract class AbstractCache<K, T> {

    static final Logger logger = LoggerFactory.getLogger(AbstractCache.class);

    protected final Class<T> objectClass;
    protected String friendlyName;
    protected String friendlyNamePl;

    private boolean autoload = true;
    private CacheState state = CacheState.NOT_LOADED;
    private boolean lazyLoadMore;

    private boolean saved;
    private Map<K, T> keyMap;

    private long addCount;
    private long removeCount;
    private long purgeCount;

    public AbstractCache(Class<T> objectClass) {
        this.objectClass = objectClass;

        String simpleName = objectClass.getSimpleName();
        friendlyName = Strings.hyphenatize(simpleName);
        friendlyNamePl = English.pluralOf(friendlyName);

        purge();
    }

    public synchronized Map<K, T> getOrLoadMap() {
        load();
        return keyMap;
    }

    public synchronized T getCached(K key) {
        return keyMap.get(key);
    }

    public synchronized T getOrLoad(K key) {
        autoload();
        T obj = keyMap.get(key);
        if (obj == null) { // lazy-loading
            if (lazyLoadMore) {
                obj = _load(key);
                if (obj != null)
                    _add(key, obj);
            }
        }
        return obj;
    }

    public synchronized void addAndWire(K key, T obj) {
        CacheState saved = state;
        state = CacheState.ADDING;

        _add(key, obj);
        _wire(obj);
        addCount++;

        state = saved;
    }

    public synchronized void unwireAndRemove(K key, T obj) {
        CacheState saved = state;
        state = CacheState.REMOVING;

        _unwire(obj);
        _remove(key, obj);
        removeCount++;

        state = saved;
    }

    /**
     * Auto load (all objects) on random access.
     */
    public void autoload() {
        if (autoload)
            load();
    }

    public synchronized boolean isLoaded() {
        return state == CacheState.LOADED;
    }

    /**
     * Load once. Do nothing if loaded.
     */
    public synchronized void load() {
        if (isLoaded())
            return;

        state = CacheState.LOADING;
        log("Loading all %s...", friendlyNamePl);
        _load();
        int n = keyMap.size();
        log("Loaded %d %s.", n, friendlyNamePl);

        state = CacheState.WIREING;
        for (T obj : keyMap.values())
            _wire(obj);

        state = CacheState.LOADED;
    }

    /**
     * Force reload.
     */
    public void reload() {
        purge();
        load();
    }

    public synchronized final void purge() {
        log("Purge cache for %s.", friendlyNamePl);
        state = CacheState.PURGING;
        _purge();
        purgeCount++;
        state = CacheState.NOT_LOADED;
    }

    public boolean isSaved() {
        return saved;
    }

    public synchronized void save() {
        if (saved)
            return;

        log("Saving %s.", friendlyNamePl);
        _save();
        log("Saved %s.", friendlyNamePl);

        saved = true;
    }

    public synchronized Entry<K, T> save(K key, T obj) {
        Entry<K, T> entry = _save(key, obj);
        K key2 = entry.getKey();
        T obj2 = entry.getValue();

        boolean added = keyMap.containsKey(key);

        if (!Nullables.equals(key, key2) || obj != obj2) {
            if (added) {
                unwireAndRemove(key, obj);
                added = false;
            }
            key = key2;
            obj = obj2;
        }

        if (!added)
            addAndWire(key, obj);
        return entry;
    }

    void log(String format, Object... args) {
        logger.logf(format, args);
    }

    protected abstract void _load();

    protected abstract T _load(K key);

    protected abstract void _save();

    protected abstract Entry<K, T> _save(K key, T obj);

    protected void _add(K key, T obj) {
        if (obj == null)
            throw new NullPointerException("obj");
        if (key != null)
            keyMap.put(key, obj);
    }

    protected void _remove(K key, T obj) {
        if (key != null)
            keyMap.remove(key);
    }

    protected void _purge() {
        keyMap = new HashMap<K, T>();
    }

    protected void _wire(T obj) {
    }

    protected void _unwire(T obj) {
    }

}
