package net.bodz.bas.site.file;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.tuple.Split;

public class ItemFile
        implements IJsonForm {

    Path file;
    String dir;
    String name;
    long size;
    String sha1;

    String origName;
    String label;
    String description;

    public ItemFile() {
    }

    public ItemFile(ItemFile o) {
        this.file = o.file;
        this.dir = o.dir;
        this.name = o.name;
        this.size = o.size;
        this.sha1 = o.sha1;
        this.origName = o.origName;
        this.label = o.label;
        this.description = o.description;
    }

    public Path getFile() {
        return file;
    }

    public void setFile(Path file) {
        this.file = file;
    }

    public String getPath() {
        if (dir == null)
            return name;
        else
            return dir + "/" + name;
    }

    public void setPath(String path) {
        Split dirBase = Split.dirBase(path);
        this.dir = dirBase.a;
        this.name = dirBase.b;
    }

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

    public String getOrigName() {
        return origName;
    }

    public void setOrigName(String origName) {
        this.origName = origName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        dir = o.getString("dir", dir);
        name = o.getString("name", name);
        size = o.getLong("size", size);
        sha1 = o.getString("sha1", sha1);
        label = o.getString("label", label);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        out.entry("dir", dir);
        out.entry("name", name);
        out.entry("size", size);
        out.entry("sha1", sha1);
        out.entry("label", label);
    }

    public static List<ItemFile> convert(JsonArray array)
            throws ParseException {
        List<ItemFile> items = new ArrayList<ItemFile>();
        for (JsonObject o : JsonFn.<JsonObject>iterate(array)) {
            ItemFile item = new ItemFile();
            item.jsonIn(JsonVariant.of(o), JsonFormOptions.DEFAULT);
            items.add(item);
        }
        return items;
    }

    @Override
    public String toString() {
        return String.format("(%s/) %s: size %,d bytes", dir, name, size);
    }

}