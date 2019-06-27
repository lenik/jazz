package net.bodz.bas.site.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonObject;

public class ItemFile
        implements IJsonSerializable {

    private String dir;
    private String name;
    private long size;
    private String sha1;
    private String label;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        dir = o.getString("dir", dir);
        name = o.getString("name", name);
        size = o.getLong("size", size);
        sha1 = o.getString("sha1", sha1);
        label = o.getString("label", label);
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.entry("dir", dir);
        out.entry("name", name);
        out.entry("size", size);
        out.entry("sha1", sha1);
        out.entry("label", label);
    }

    public static List<ItemFile> convert(JSONArray array)
            throws ParseException {
        List<ItemFile> items = new ArrayList<>();
        for (JSONObject o : JsonFn.<JSONObject> iterate(array)) {
            ItemFile item = new ItemFile();
            item.readObject(JsonObject.wrap(o));
            items.add(item);
        }
        return items;
    }

    @Override
    public String toString() {
        return String.format("(%s)/%s: size %,d bytes", dir, name, size);
    }

}