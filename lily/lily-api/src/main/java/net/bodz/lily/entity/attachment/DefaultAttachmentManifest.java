package net.bodz.lily.entity.attachment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import net.bodz.bas.c.java.util.regex.GlobPattern;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;

public class DefaultAttachmentManifest
        implements IAttachmentManifest,
                   IMutableAttachmentManifest<MutableAttachment>,
                   Iterable<MutableAttachment>,
                   IJsonForm {

    List<MutableAttachment> data = new ArrayList<>();

    @Override
    public List<? extends IAttachment> getData() {
        return data;
    }

    @NotNull
//    @Override
    public Iterator<MutableAttachment> iterator() {
        return data.iterator();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void add(@NotNull MutableAttachment attachment) {
        data.add(attachment);
    }

    @Override
    public MutableAttachment get(int index) {
        return data.get(index);
    }

    @Override
    public void set(int index, @NotNull MutableAttachment attachment) {
        data.set(index, attachment);
    }

    @Override
    public MutableAttachment get(@NotNull String key) {
        for (MutableAttachment item : data) {
            if (key.equals(item.getKey()))
                return item;
        }
        return null;
    }

    @Override
    public void set(@NotNull String key, @NotNull MutableAttachment attachment, boolean autoAppend) {
        int n = size();
        for (int i = 0; i < n; i++) {
            MutableAttachment item = data.get(i);
            if (key.equals(item.getKey())) {
                set(i, attachment);
                return;
            }
        }
        if (autoAppend)
            add(attachment);
    }

    @NotNull
    @Override
    public MutableAttachment remove(int index) {
        return data.remove(index);
    }

    @Nullable
    @Override
    public MutableAttachment remove(@NotNull String key) {
        int n = size();
        for (int i = 0; i < n; i++) {
            MutableAttachment item = data.get(i);
            if (key.equals(item.getKey()))
                return data.remove(i);
        }
        return null;
    }

    @Override
    public void clear() {
        data.clear();
    }

    public MutableAttachment getByFileName(@NotNull String name) {
        for (MutableAttachment item : data)
            if (name.equals(item.getFileName()))
                return item;
        return null;
    }

    @Override
    public MutableAttachment find(@NotNull String glob, boolean ignoreCase, int index) {
        if (index < 0)
            return null;
        boolean fuzzy = glob.contains("*") || glob.contains("?") || ignoreCase;
        if (!fuzzy) {
            if (index != 0)
                return null;
            return getByFileName(glob);
        }
        GlobPattern pattern = GlobPattern.compile(glob);
        int i = 0;
        for (MutableAttachment item : this) {
            String fileName = item.getFileName();
            if (pattern.matcher(fileName).matches())
                if (i == index)
                    return item;
                else
                    i++;
        }
        return null;
    }

    @Nullable
    @Override
    public MutableAttachment find(@NotNull Predicate<? super IAttachment> predicate, int index) {
        if (index < 0)
            return null;
        int i = 0;
        for (MutableAttachment item : this) {
            if (predicate.test(item))
                if (i == index)
                    return item;
                else
                    i++;
        }
        return null;
    }

    @NotNull
    @Override
    public List<MutableAttachment> filter(@NotNull Predicate<? super IAttachment> predicate) {
        List<MutableAttachment> list = new ArrayList<>();
        for (MutableAttachment item : this) {
            if (predicate.test(item))
                list.add(item);
        }
        return list;
    }

    @Override
    public void find(@NotNull Predicate<? super IAttachment> predicate, int index, @Nullable MutableAttachment replacement, boolean autoAppend) {
        int insert;
        if (index < 0)
            insert = -1; // -0 - 1);
        else {
            int n = size();
            for (int i = 0; i < n; i++) {
                IAttachment item = get(i);
                if (predicate.test(item))
                    if (i == index) {
                        insert = i;
                        break;
                    } else
                        i++;
            }
            insert = -n - 1;
        }
        if (insert >= 0)
            if (replacement == null)
                data.remove(insert);
            else
                data.set(insert, replacement);
        else {
            if (replacement != null)
                if (autoAppend)
                    data.add(-insert - 1, replacement);
        }
    }

    @Override
    public void filter(@NotNull Predicate<? super IAttachment> predicate, @NotNull Collection<? extends MutableAttachment> replacement, boolean autoAppend) {
        List<MutableAttachment> request = new ArrayList<>(replacement);
        Iterator<MutableAttachment> iterator = iterator();
        while (iterator.hasNext()) {
            MutableAttachment item = iterator.next();
            if (predicate.test(item)) {
                int index = request.indexOf(item);
                if (index == -1)
                    iterator.remove();
                else
                    request.set(index, null);
            }
        }
        if (autoAppend)
            for (MutableAttachment a : request)
                if (a != null)
                    data.add(a);
    }

    @Override
    public AttThumbnail findThumbnail(IAttachment a, ImageType type) {
        AttThumbnailSet set = a.getThumbnails();
        if (set == null)
            return null;
        return set.matchFind(type);
    }

    @Override
    public AttPreview findPreview(IAttachment a, VideoType type) {
        AttPreviewSet set = a.getPreviews();
        if (set == null)
            return null;
        return set.matchFind(type);
    }

    @Override
    public AttScreenpack findScreenpack(IAttachment a, ImageType type) {
        AttScreenpackSet set = a.getScreenshots();
        if (set == null)
            return null;
        return set.matchFind(type);
    }

    @Override
    public void onAttachmentPathChanged(AttachmentPathChangeEvent event) {
        event.getNewVolume();
        event.getNewPath();
    }

    private static final String K_DATA = "data";
    private static final String K_THUMBNAILS = "thumb";
    private static final String K_PREVIEWS = "preview";
    private static final String K_SCREENPACKS = "screen";


    void reloadData(@NotNull JsonArray array, JsonFormOptions opts) {
        data.clear();
        int n = array.length();
        for (int i = 0; i < n; i++) {
            JsonObject jo = array.getJsonObject(i);
            MutableAttachment item = new MutableAttachment();
            item.jsonIn(jo, opts);
            data.add(item);
        }
    }

    @Override
    public void jsonIn(JsonVariant in, JsonFormOptions opts)
            throws ParseException {
        if (in.isArray())
            reloadData(in.getArray1(), opts);
        else
            jsonIn(in.getObject1(), opts);
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        JsonArray ja = o.getJsonArray(K_DATA);
        reloadData(ja, opts);

        ja = o.getJsonArray(K_THUMBNAILS);
        if (ja != null) {
            List<AttThumbnailSet> list = new ArrayList<>();
            o.readArrayIntoList(K_THUMBNAILS, list, AttThumbnailSet::new);
            for (int i = 0; i < list.size(); i++) {
                AttThumbnailSet set = list.get(i);
                if (set != null)
                    data.get(i).setView(AttThumbnailSet.class, set);
            }
        }

        ja = o.getJsonArray(K_PREVIEWS);
        if (ja != null) {
            List<AttPreviewSet> list = new ArrayList<>();
            o.readArrayIntoList(K_PREVIEWS, list, AttPreviewSet::new);
            for (int i = 0; i < list.size(); i++) {
                AttPreviewSet set = list.get(i);
                if (set != null)
                    data.get(i).setView(AttPreviewSet.class, set);
            }
        }

        ja = o.getJsonArray(K_SCREENPACKS);
        if (ja != null) {
            List<AttScreenpackSet> list = new ArrayList<>();
            o.readArrayIntoList(K_SCREENPACKS, list, AttScreenpackSet::new);
            for (int i = 0; i < list.size(); i++) {
                AttScreenpackSet set = list.get(i);
                if (set != null)
                    data.get(i).setView(AttScreenpackSet.class, set);
            }
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        if (data != null) {
            out.key(K_DATA);
            out.array();
            for (MutableAttachment item : data)
                item.jsonOutValue(out, opts);
            out.endArray();
        }

        out.key(K_THUMBNAILS);
        out.array();
        for (IAttachment item : data) {
            AttThumbnailSet set = item.getThumbnails();
            if (set != null && !set.isEmpty()) {
                out.object();
                set.jsonOut(out, opts);
                out.endObject();
            } else
                out.value(null);
        }
        out.endArray();

        out.key(K_PREVIEWS);
        out.array();
        for (IAttachment item : data) {
            AttPreviewSet set = item.getPreviews();
            if (set != null && !set.isEmpty()) {
                out.object();
                set.jsonOut(out, opts);
                out.endObject();
            } else
                out.value(null);
        }
        out.endArray();

        out.key(K_SCREENPACKS);
        out.array();
        for (IAttachment item : data) {
            AttScreenpackSet set = item.getScreenshots();
            if (set != null && !set.isEmpty()) {
                out.object();
                set.jsonOut(out, opts);
                out.endObject();
            } else
                out.value(null);
        }
        out.endArray();
    }


}