package net.bodz.bas.c.object;

import java.util.Map.Entry;
import java.util.Set;

public class RelationMapManager {

    private IdentityObjectMap<RelationMap> objects;

    public RelationMapManager() {
        objects = new IdentityObjectMap<RelationMap>();
    }

    public Set<Entry<Object, RelationMap>> entrySet() {
        return objects.entrySet();
    }

    public synchronized RelationMap get(Object object) {
        if (object == null)
            throw new NullPointerException("object");
        RelationMap relationMap = objects.get(object);
        if (relationMap == null) {
            relationMap = new RelationMap();
            objects.put(object, relationMap);
        }
        return relationMap;
    }

}
