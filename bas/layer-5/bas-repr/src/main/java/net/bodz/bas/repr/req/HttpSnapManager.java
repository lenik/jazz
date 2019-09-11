package net.bodz.bas.repr.req;

import java.util.HashMap;
import java.util.Map;

public class HttpSnapManager {

    public static final String ATTRIBUTE_KEY = HttpSnapManager.class.getName();

    private Map<Long, HttpSnap> snaps;
    private long nextId = 1;

    public HttpSnapManager() {
        snaps = new HashMap<Long, HttpSnap>();
    }

    public HttpSnap get(long id) {
        return snaps.get(id);
    }

    public synchronized HttpSnap create() {
        while (snaps.containsKey(nextId))
            nextId++;
        long id = nextId++;
        HttpSnap snap = new HttpSnap(id);
        snaps.put(id, snap);
        return snap;
    }

    public void reset() {
        snaps.clear();
    }

}
