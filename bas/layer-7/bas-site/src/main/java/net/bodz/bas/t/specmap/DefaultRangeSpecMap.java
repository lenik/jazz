package net.bodz.bas.t.specmap;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.repr.form.SortOrder;

public class DefaultRangeSpecMap<key_t extends Comparable<key_t>, val_t>
        extends AbstractRangeSpecMap<key_t, val_t>
        implements
            IRangeMap<key_t, val_t> {

    private Map<key_t, val_t> map;
    private TreeMap<IRange<key_t>, val_t> rangeMap;

    public DefaultRangeSpecMap() {
        map = SortOrder.ASCENDING.newMap();
        rangeMap = new TreeMap<>(RangeComparator.getInstance());
    }

    @Override
    public Map<key_t, val_t> getMap() {
        return map;
    }

    @Override
    public TreeMap<IRange<key_t>, val_t> getRangeMap() {
        return rangeMap;
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        super.jsonOut(out, opts);
        out.entry("map", map);
        out.entry("rangeMap", rangeMap);
    }

}
