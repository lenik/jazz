package net.bodz.lily.entity.attachment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.t.tuple.Split;

public class DefaultAttachment
        implements
            IAttachment {

    IAttachmentVolume volume;
    String dirName;
    String fileName;
    String _path;

    String label;
    String description;

    Boolean symLinkToSha1;
    Long size;
    String sha1;

    public DefaultAttachment() {
    }

    public DefaultAttachment(IAttachmentVolume volume, String dirName, String fileName) {
        this.volume = volume;
        this.dirName = dirName;
        this.fileName = fileName;
        updatePath();
    }

    public DefaultAttachment(IAttachmentVolume volume, String path) {
        this.volume = volume;
        setPath(path);
    }

    public DefaultAttachment(DefaultAttachment o) {
        this.volume = o.volume;
        this.dirName = o.dirName;
        this.fileName = o.fileName;
        this._path = o._path;
        this.label = o.label;
        this.description = o.description;
        this.symLinkToSha1 = o.symLinkToSha1;
        this.size = o.size;
        this.sha1 = o.sha1;
    }

    public DefaultAttachment(IAttachment o)
            throws IOException {
        this.volume = o.getVolume();
        setPath(o.getPath());
        this.label = o.getLabel();
        this.description = o.getDescription();
    }

    @Override
    public IAttachmentVolume getVolume() {
        return volume;
    }

    @Override
    public void setVolume(IAttachmentVolume volume) {
        if (this.volume != volume) {
            this.volume = volume;
            invalidate();
        }
    }

    @Override
    public IAnchor getAnchor() {
        String path = getPath();
        return volume.getVolumeAnchor().join(path);
    }

    @Override
    public String getDirName() {
        return dirName;
    }

    @Override
    public void setDirName(String dirName) {
        if (Nullables.notEquals(this.dirName, dirName)) {
            this.dirName = dirName;
            updatePath();
            invalidate();
        }
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        if (Nullables.notEquals(this.fileName, fileName)) {
            this.fileName = fileName;
            updatePath();
            invalidate();
        }
    }

    @Override
    public String getPath() {
        return _path;
    }

    @Override
    public void setPath(String path) {
        if (Nullables.notEquals(this._path, path)) {
            if (path == null) {
                dirName = fileName = null;
            } else {
                Split dirBase = Split.dirBase(path);
                dirName = dirBase.a;
                fileName = dirBase.b;
            }
            _path = path;
            invalidate();
        }
    }

    void updatePath() {
        String path = fileName;
        if (dirName != null)
            path = dirName + "/" + fileName;
        this._path = path;
    }

    @Override
    public String getName() {
        if (fileName == null)
            return null;
        else
            return Split.nameExtension(fileName).a;
    }

    @Override
    public void setName(String name) {
        String newFileName;
        if (fileName == null)
            newFileName = name;
        else
            newFileName = Split.nameExtension(fileName).first(name).join('.');
        setFileName(newFileName);
    }

    @Override
    public String getExtension() {
        if (fileName == null)
            return null;
        else
            return Split.nameExtension(fileName).b;
    }

    @Override
    public void setExtension(String extension) {
        String newFileName;
        if (fileName == null)
            newFileName = "." + extension;
        else
            newFileName = Split.nameExtension(fileName).second(extension).join('.');
        setFileName(newFileName);
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
    public File getFile() {
        return volume.getLocalFile(_path);
    }

    @Override
    public boolean exists() {
        return volume.exists(_path);
    }

    @Override
    public synchronized boolean isSymLinkToSha1()
            throws IOException {
        if (symLinkToSha1 == null) {
            if (!volume.isSymLink(_path))
                return false;
            String target = volume.getSymLinkTarget(_path);
            String name = Split.nameExtension(target).a;
            String sha1 = getSHA1();
            if (name == null || sha1 == null)
                return false;
            symLinkToSha1 = name.equalsIgnoreCase(sha1);
        }
        return symLinkToSha1.booleanValue();
    }

    @Override
    public long getSize()
            throws FileNotFoundException {
        if (size == null)
            size = volume.getSize(_path);
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public synchronized String getSHA1()
            throws IOException {
        if (sha1 == null)
            sha1 = volume.getSHA1(_path);
        return sha1;
    }

    public void setSHA1(String sha1) {
        this.sha1 = sha1;
    }

    @Override
    public IAttachment getPreviewImage(int desiredWidth, int desiredHeight) {
        return null;
    }

    void invalidate() {
        symLinkToSha1 = null;
        size = null;
        sha1 = null;
    }

    @Override
    public synchronized void update()
            throws IOException {
        invalidate();
        isSymLinkToSha1();
        getSize();
        getSHA1();
    }

    @Override
    public String toString() {
        return getPath();
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        HttpServletRequest request = CurrentHttpService.getRequest();
        String volumeId = o.getString(K_VOLUME_ID);
        volume = AttachmentGroup.forRequest(request).getVolume(volumeId);
        setPath(o.getString(K_PATH));
        label = o.getString(K_LABEL);
        description = o.getString(K_DESCRIPTION);
        symLinkToSha1 = o.getBoolean(K_SYMLINK_TO_SHA1);
        size = o.getLong(K_SIZE);
        sha1 = o.getString(K_SHA1);
    }

    public static List<DefaultAttachment> convert(JsonArray array)
            throws ParseException {
        List<DefaultAttachment> items = new ArrayList<>();
        for (JsonObject o : JsonFn.<JsonObject> iterate(array)) {
            DefaultAttachment item = new DefaultAttachment();
            item.jsonIn(o, JsonFormOptions.DEFAULT);
            items.add(item);
        }
        return items;
    }

}
