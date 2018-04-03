package net.bodz.bas.site.file;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonSupport;

public class ItemFile
        extends JsonSupport {

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

    public static List<ItemFile> convert(JSONArray array)
            throws ParseException {
        List<ItemFile> items = new ArrayList<>();
        for (JSONObject el : JsonFn.<JSONObject> iterate(array)) {
            ItemFile item = new ItemFile();
            item.readObject(el);
            items.add(item);
        }
        return items;
    }

}