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

public class AttPreviewSet
        extends LinkedHashMap<String, AttPreview>
        implements Iterable<AttPreview>,
                   IJsonForm {

    @Serial
    private static final long serialVersionUID = 1L;

    public List<AttPreview> matchSort(VideoType type) {
        VideoLikeTypeComparator order = new VideoLikeTypeComparator(type);
        List<AttPreview> copy = new ArrayList<>(values());
        copy.sort(order);
        return copy;
    }

    public AttPreview matchFind(VideoType type) {
        List<AttPreview> sorted = matchSort(type);
        VideoLikeTypeComparator order = new VideoLikeTypeComparator(type);
        return Lists.binarySearch1(sorted, type, order);
    }

    @NotNull
    @Override
    public Iterator<AttPreview> iterator() {
        return values().iterator();
    }

    /**
     * preview: {
     *     320x180: { backedFile },
     *     ...
     * }
     */
    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        clear();
        for (String key : o.keySet()) {
            JsonObject child = o.getJsonObject(key);
            AttPreview item = new AttPreview();
            item.jsonIn(child, opts);
            put(key, item);
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        for (String key : keySet()) {
            AttPreview item = get(key);
            // out.key(item.getTypeKey());
            out.key(key);
            item.jsonOutValue(out, opts);
        }
    }

}
