package net.bodz.lily.entity.attachment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.bodz.bas.c.org.json.JsonStringer;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.file.BottomUpPathFields;
import net.bodz.lily.storage.IVolume;
import net.bodz.lily.storage.IVolumeItem;

public class DefaultBackedFile
        extends BottomUpPathFields
        implements IBackedFile {

    long size;
    String sha1;

    public DefaultBackedFile() {
    }

    public DefaultBackedFile path(String dirName, String fileName) {
        setPath(dirName, fileName);
        return this;
    }

    public DefaultBackedFile path(String path) {
        setPath(path);
        return this;
    }

    public DefaultBackedFile assign(IBackedFile o)
            throws IOException {
        setPath(o);
        this.size = o.getFileSize();
        this.sha1 = o.getFileSHA1();
        return this;
    }

    public DefaultBackedFile assign(DefaultBackedFile o) {
        setPath(o);
        this.size = o.size;
        this.sha1 = o.sha1;
        return this;
    }

    @Override
    public long getFileSize() {
        return size;
    }

    public void setFileSize(long size) {
        this.size = size;
    }

    @Override
    public String getFileSHA1() {
        return sha1;
    }

    public void setFileSHA1(String sha1) {
        this.sha1 = sha1;
    }

    @Override
    public synchronized void update(IVolume volume)
            throws IOException {
        IVolumeItem vf = toVolumeFile(volume);
        size = vf.getSize();
        sha1 = vf.getSHA1();
    }

    @Override
    public String toString() {
        return getPath();
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts) {
        super.jsonIn(o, opts);
        size = o.getLong(K_FILE_SIZE, size);
        sha1 = o.getString(K_FILE_SHA1);
    }

    public static List<DefaultBackedFile> parseBackedFiles(JsonArray array) {
        List<DefaultBackedFile> result = new ArrayList<>();
        try {
            parseJsonArray(array, result, DefaultBackedFile::new);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void parseBackedFiles(JsonArray array, List<DefaultBackedFile> result) {
        try {
            parseJsonArray(array, result, DefaultBackedFile::new);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends IBackedFile> List<T> parseJsonArray(JsonArray array, Supplier<T> factory)
            throws ParseException {
        List<T> result = new ArrayList<>();
        parseJsonArray(array, result, factory);
        return result;
    }

    public static <T extends IBackedFile> void parseJsonArray(JsonArray array, List<T> result, Supplier<T> factory)
            throws ParseException {
        if (array != null)
            for (JsonObject o : JsonFn.<JsonObject>iterate(array)) {
                T item = factory.get();
                item.jsonIn(o, JsonFormOptions.DEFAULT);
                result.add(item);
            }
    }

    public static JsonArray toJsonArray(List<? extends IBackedFile> items) {
        JsonArray ja = new JsonArray();
        if (items == null)
            return null;
        for (IBackedFile item : items) {
            JsonStringer buf = new JsonStringer();
            try {
                item.jsonOut(buf);
                String json = buf.toString();
                JsonObject jo = JsonFn.parseObject(json);
                ja.put(jo);
            } catch (IOException | FormatException e) {
                throw new UnexpectedException(e.getMessage(), e);
            } catch (ParseException e) {
                throw new UnexpectedException(e.getMessage(), e);
            }
        }
        return ja;
    }

}
