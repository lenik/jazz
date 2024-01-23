package net.bodz.lily.entity.attachment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.file.BottomUpPathFields;
import net.bodz.lily.storage.IVolume;
import net.bodz.lily.storage.IVolumeItem;

public class DefaultAttachment
        extends BottomUpPathFields
        implements
            IAttachment {

    String label;
    String description;

    long size;
    String sha1;

    public DefaultAttachment() {
    }

    public DefaultAttachment(String dirName, String fileName) {
        setPath(dirName, fileName);
    }

    public DefaultAttachment(String path) {
        setPath(path);
    }

    public DefaultAttachment(DefaultAttachment o) {
        super(o);
        this.label = o.label;
        this.description = o.description;
        this.size = o.size;
        this.sha1 = o.sha1;
    }

    public DefaultAttachment(IAttachment o)
            throws IOException {
        setPath(o.getPath());
        this.label = o.getLabel();
        this.description = o.getDescription();
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
    public IAttachment getPreviewImage(int desiredWidth, int desiredHeight) {
        return null;
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
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);
        label = o.getString(K_LABEL);
        description = o.getString(K_DESCRIPTION);
        size = o.getLong(K_FILE_SIZE);
        sha1 = o.getString(K_FILE_SHA1);
    }

    public static List<IAttachment> parseJsonArray(JsonArray array)
            throws ParseException {
        List<IAttachment> items = new ArrayList<>();
        for (JsonObject o : JsonFn.<JsonObject> iterate(array)) {
            DefaultAttachment item = new DefaultAttachment();
            item.jsonIn(o, JsonFormOptions.DEFAULT);
            items.add(item);
        }
        return items;
    }

}
