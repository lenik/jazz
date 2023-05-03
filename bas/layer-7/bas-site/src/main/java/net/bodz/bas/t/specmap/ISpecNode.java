package net.bodz.bas.t.specmap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ISpecNode<node_t extends ISpecNode<node_t, key_t, val_t>, key_t, val_t>
        extends
            ISpecMap<key_t, node_t>,
            IValueHolder<val_t> {

    Class<key_t> getKeyType();

    node_t getParent();

    boolean isEmptyNode();

    default Iterable<List<key_t>> topPaths() {
        return new Iterable<List<key_t>>() {
            @Override
            public Iterator<List<key_t>> iterator() {
                @SuppressWarnings("unchecked")
                node_t _this = (node_t) ISpecNode.this;
                return new SpecNodeTopPathIterator<node_t, key_t>(_this);
            }
        };
    }

    /**
     * @return Non-<code>null</code> value. Maybe empty.
     */
    default List<ILayerKeyValue<node_t>> findAll(key_t[] path) {
        @SuppressWarnings("unchecked")
        node_t node = (node_t) this;

        List<ILayerKeyValue<node_t>> curs = new ArrayList<>();
        curs.add(LayerKeyValue._default(node));

        List<ILayerKeyValue<node_t>> nexts = new ArrayList<>();

        for (int i = 0; i < path.length; i++) {
            for (ILayerKeyValue<node_t> cur : curs)
                for (ILayerKeyValue<node_t> next : cur.getValue().findAll(path[i]))
                    nexts.add(next);

            if (nexts.isEmpty())
                return nexts;

            List<ILayerKeyValue<node_t>> tmp = curs;
            curs = nexts;
            nexts = tmp;
            nexts.clear();
        }
        return curs;
    }

    default ILayerKeyValue<node_t> findFirst(key_t[] path) {
        List<ILayerKeyValue<node_t>> list = findAll(path);
        if (list.isEmpty())
            return null;
        else
            return list.get(0);
    }

    default node_t find(key_t[] path) {
        return find(path, 0);
    }

    default node_t find(key_t[] path, int off) {
        if (off == path.length) {
            @SuppressWarnings("unchecked")
            node_t _this = (node_t) this;
            return _this;
        }
        key_t head = path[off++];
        for (ILayerKeyValue<node_t> kv : findAll(head)) {
            node_t next = kv.getValue().find(path, off);
            if (next != null)
                return next;
        }
        return null;
    }

    default node_t lazyCreate(key_t[] path) {
        return lazyCreate(path, 0);
    }

    default node_t lazyCreate(key_t[] path, int off) {
        if (off == path.length) {
            @SuppressWarnings("unchecked")
            node_t _this = (node_t) this;
            return _this;
        }
        key_t head = path[off++];
        node_t next = lazyCreate(head);
        return next.lazyCreate(path, off + 1);
    }

    default node_t lazyCreate(key_t key) {
        node_t node;
        if (key != null) {
            node = getTop(key);
            if (node == null)
                putTop(key, node = createNode());
        } else {
            node = getDefault();
            if (node == null)
                putDefault(node = createNode());
        }
        return node;
    }

    node_t createNode();

    void accept(ISpecNodeVisitor<? super node_t, ? super key_t, ? super val_t> visitor);

    default Map<key_t[], node_t> scanTerms() {
        Map<key_t[], node_t> nodes = new LinkedHashMap<>();

        Class<key_t> keyType = getKeyType();

        @SuppressWarnings("unchecked")
        final key_t[] keySig = (key_t[]) Array.newInstance(keyType, 0);

        class NodeIterator
                implements
                    ISpecNodeVisitor<node_t, key_t, val_t> {

            List<key_t> path = new ArrayList<>();
            int last;

            @Override
            public boolean beginTops() {
                last = path.size();
                path.add(null);
                return true;
            }

            @Override
            public boolean visitTop(key_t key, node_t val) {
                path.set(last, key);
                return true;
            }

            @Override
            public void endTops() {
                path.remove(last);
                last--;
            }

            @Override
            public boolean beginNode(node_t node) {
                if (node.hasValue()) {
                    key_t[] array = path.toArray(keySig);
                    nodes.put(array, node);
                }
                return true;
            }
        }
        accept(new NodeIterator());
        return nodes;
    }

}
