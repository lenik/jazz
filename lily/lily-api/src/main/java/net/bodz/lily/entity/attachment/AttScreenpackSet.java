package net.bodz.lily.entity.attachment;

import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import net.bodz.bas.c.java.util.Lists;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class AttScreenpackSet
        extends LinkedHashMap<String, AttScreenpack>
        implements Iterable<AttScreenpack>,
                   IJsonForm {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Override
    public Iterator<AttScreenpack> iterator() {
        return values().iterator();
    }

    List<AttScreenpack> matchSort(ImageType type) {
        ImageLikeTypeComparator order = new ImageLikeTypeComparator(type);
        List<AttScreenpack> copy = new ArrayList<>(values());
        copy.sort(order);
        return copy;
    }

    public AttScreenpack matchFind(ImageType type) {
        List<AttScreenpack> sorted = matchSort(type);
        ImageLikeTypeComparator order = new ImageLikeTypeComparator(type);
        return Lists.binarySearch1(sorted, type, order);
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        clear();
        for (String key : o.keySet()) {
            JsonObject child = o.getJsonObject(key);
            AttScreenpack item = new AttScreenpack();
            item.jsonIn(child, opts);
            put(key, item);
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        for (String key : keySet()) {
            out.key(key);
            AttScreenpack item = get(key);
            out.object();
            item.jsonOut(out, opts);
            out.endObject();
        }
    }

}
