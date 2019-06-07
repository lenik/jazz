package net.bodz.lily.model.base;

import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.db.ctx.DataContext;

public abstract class AbstractCoNodeCache<node_t extends CoNode<node_t, K>, K>
        extends AbstractCoEntityCache<node_t, K> {

    Map<K, node_t> rootMap;

    public AbstractCoNodeCache(DataContext dataContext, Class<node_t> entityClass) {
        super(dataContext, entityClass);
    }

    public Map<K, node_t> getOrLoadRootMap() {
        autoload();
        return rootMap;
    }

    @Override
    protected void _add(K id, node_t obj) {
        super._add(id, obj);

        if (obj.getParent() == null)
            rootMap.put(id, obj);
    }

    @Override
    protected void _remove(K id, node_t obj) {
        if (obj.getParent() == null)
            rootMap.remove(id);

        super._remove(id, obj);
    }

    @Override
    protected void _purge() {
        super._purge();
        rootMap = new LinkedHashMap<>();
    }

    @Override
    protected void _wire(node_t obj) {
        node_t parent = obj.getParent();
        K parentId = parent == null ? null : parent.getId();
        node_t cachedParent = parentId == null ? null : getCached(parentId);
        if (parent != cachedParent) {
            obj.attach(cachedParent);
        }
    }

    @Override
    protected void _unwire(node_t obj) {
        node_t parent = obj.getParent();
        K parentId = parent == null ? null : parent.getId();
        node_t cachedParent = parentId == null ? null : getCached(parentId);
        if (parent == cachedParent) {
            node_t parentHolder = create();
            parentHolder.setId(parentId);
            obj.setParent(parentHolder);
        }
    }

    public node_t createPseudoRoot() {
        node_t pseudoRoot = create();
        for (node_t root : getOrLoadRootMap().values()) {
            // don't attach here. avoid concurrent problems.
            pseudoRoot.getChildren().add(root);
        }
        return pseudoRoot;
    }

}
