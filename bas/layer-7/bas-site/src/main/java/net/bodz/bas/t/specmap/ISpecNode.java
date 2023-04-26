package net.bodz.bas.t.specmap;

import java.util.ArrayList;
import java.util.List;

public interface ISpecNode<node_t extends ISpecNode<node_t, key_t, val_t>, key_t, val_t>
        extends
            ISpecMap<key_t, node_t>,
            IValueHolder<val_t> {

    node_t getParent();

    default node_t find(key_t[] path) {
        List<ILayerKeyValue<node_t>> list = findAll(path);
        if (list.isEmpty())
            return null;
        else
            return list.get(0).getValue();
    }

    default ILayerKeyValue<node_t> findFirst(key_t[] path) {
        List<ILayerKeyValue<node_t>> list = findAll(path);
        if (list.isEmpty())
            return null;
        else
            return list.get(0);
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

    default node_t create(key_t[] path) {
        return create(path, false);
    }

    default node_t create(key_t[] path, boolean overwrite) {
        @SuppressWarnings("unchecked")
        node_t node = (node_t) this;

        for (int i = 0; i < path.length; i++) {
            node_t next = node.find(path[i]);
            if (next == null) {
                next = node.create(path[i], overwrite);
                if (next == null)
                    return null;
            }
            node = next;
        }
        return node;
    }

    default node_t create(key_t key, boolean overwrite) {
        node_t node = createNode();
        if (overwrite) {
            if (key != null)
                put(key, node);
            else
                setDefault(node);
        } else {
            if (key != null) {
                if (!add(key, node))
                    return null;
            } else {
                if (!addDefault(node))
                    return null;
            }
        }
        return node;
    }

    node_t createNode();

    void accept(ISpecNodeVisitor<? super node_t, ? super key_t, ? super val_t> visitor);

}
