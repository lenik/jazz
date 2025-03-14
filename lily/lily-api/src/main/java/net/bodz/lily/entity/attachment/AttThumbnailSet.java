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

public class AttThumbnailSet
        extends LinkedHashMap<String, AttThumbnail>
        implements Iterable<AttThumbnail>,
                   IJsonForm {

    @Serial
    private static final long serialVersionUID = 1L;

    List<AttThumbnail> matchSort(ImageType type) {
        ImageLikeTypeComparator order = new ImageLikeTypeComparator(type);
        List<AttThumbnail> copy = new ArrayList<>(values());
        copy.sort(order);
        return copy;
    }

    public AttThumbnail matchFind(ImageType type) {
        List<AttThumbnail> sorted = matchSort(type);
        ImageLikeTypeComparator order = new ImageLikeTypeComparator(type);
        return Lists.binarySearch1(sorted, type, order);
    }

    @NotNull
    @Override
    public Iterator<AttThumbnail> iterator() {
        return values().iterator();
    }

    /**
     * preview: {
     *     320x180: { backedFile },
     *     ...
     * }
     */
    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        clear();
        for (String key : o.keySet()) {
            JsonObject child = o.getJsonObject(key);
            AttThumbnail item = new AttThumbnail();
            item.jsonIn(child, opts);
            put(key, item);
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        for (String key : keySet()) {
            AttThumbnail item = get(key);
            out.key(key);
            item.jsonOutValue(out, opts);
        }
    }

}
