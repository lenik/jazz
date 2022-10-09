package net.bodz.lily.model.base;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.c.object.IdentityHashSet;
import net.bodz.bas.db.cache.AbstractCache;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.pojo.Pair;
import net.bodz.lily.entity.IdFn;

public abstract class AbstractCoEntityCache<T extends CoEntity<K>, K>
        extends AbstractCache<K, T> {

    static final Logger logger = LoggerFactory.getLogger(AbstractCoEntityCache.class);

    DataContext dataContext;
    Class<K> idType;

    IEntityMapper<T, ?> mapper;
    int batch = 100;

    protected boolean indexCodeName = false;
    protected boolean indexLabel = false;

    private Map<String, T> codeNameMap;
    private Map<String, Set<T>> labelMap;

    public AbstractCoEntityCache(DataContext dataContext, Class<T> entityClass) {
        super(entityClass);
        if (dataContext == null)
            throw new NullPointerException("dataContext");
        this.dataContext = dataContext;
        this.idType = IdFn._getIdType(entityClass);
    }

    protected IEntityMapper<T, ?> getMapper() {
        if (mapper == null) {
            Class<IEntityMapper<T, ?>> mapperClass = IMapper.fn.getMapperClass(objectClass);
            mapper = dataContext.getMapper(mapperClass);
        }
        return mapper;
    }

    public void setMapper(IEntityMapper<T, ?> mapper) {
        this.mapper = mapper;
    }

    public synchronized Map<String, T> getOrLoadCodeNameMap() {
        if (!indexCodeName) {
            logger.error("Code name isn't indexed.");
            return Collections.emptyMap();
        }
        load();
        return codeNameMap;
    }

    public synchronized Map<String, Set<T>> getOrLoadLabelMap() {
        if (!indexLabel) {
            logger.error("Label isn't indexed.");
            return Collections.emptyMap();
        }
        load();
        return labelMap;
    }

    protected Set<T> labelSet(String label, boolean create) {
        if (!indexLabel)
            return Collections.emptySet();

        Set<T> set = labelMap.get(label);
        if (set == null) {
            if (create)
                labelMap.put(label, set = createSet());
            else
                set = Collections.emptySet();
        }
        return set;
    }

    protected Set<T> createSet() {
        return new IdentityHashSet<>();
    }

    public synchronized T getOrLoadByCodeName(String codeName) {
        if (!indexCodeName)
            logger.warn("code name isn't indexed.");

        autoload();

        T obj = codeNameMap.get(codeName);
        if (obj == null) {
            obj = getMapper().selectByCodeName(codeName);
            if (obj != null)
                _add(obj.id(), obj);
        }
        return obj;
    }

    public T save(T obj) {
        K key = obj.id();
        Entry<K, T> other = save(key, obj);
        return other.getValue();
    }

    @Override
    protected void _load() {
        IEntityMapper<T, ?> mapper = getMapper();
        int n = 0;
        for (T obj : mapper.all(null)) {
            if (n % batch == 0) {
                logger.debug(String.format("Loading %d-%d of %s...", n - batch + 1, n, friendlyNamePl));
            }
            n++;
            _add(obj.id(), obj);
        }
    }

    @Override
    protected T _load(K key) {
        T obj = getMapper().select(key);
        return obj;
    }

    @Override
    protected void _save() {
        throw new NotImplementedException();
    }

    @Override
    protected Entry<K, T> _save(K key, T obj) {
        if (key == null) {
            getMapper().insert(obj);
            // obj.id is filled now.
        } else {
            getMapper().update(obj);
            // any auto generated fields..?
        }
        return Pair.of(key, obj);
    }

    @Override
    protected void _add(K id, T obj) {
        super._add(id, obj);

        if (indexCodeName) {
            String codeName = obj.getCodeName();
            if (codeName != null)
                codeNameMap.put(codeName, obj);
        }

        if (indexLabel) {
            String label = obj.getLabel();
            if (label != null)
                labelSet(label, true).add(obj);
        }
    }

    @Override
    protected void _remove(K id, T obj) {
        if (indexCodeName) {
            String codeName = obj.getCodeName();
            if (codeName != null)
                codeNameMap.remove(codeName);
        }

        if (indexLabel) {
            String label = obj.getLabel();
            if (label != null)
                labelSet(label, false).remove(obj);
        }

        super._remove(id, obj);
    }

    @Override
    protected void _purge() {
        super._purge();
        codeNameMap = new HashMap<>();
        labelMap = new HashMap<>();
    }

    protected T create() {
        try {
            return objectClass.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new CreateException(String.format(//
                    "Can't instantiate %s: %s", objectClass, e.getMessage()), e);
        }
    }

}
