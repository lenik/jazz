package net.bodz.bas.site.file;

import java.io.IOException;

import javax.servlet.http.Part;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;

public class UploadedFileInfo
        extends ItemFile {

    String path;
    String url;
    String thumbnail;
    String deleteUrl;
    String deleteType = "DELETE";

    public UploadedFileInfo() {
    }

    public UploadedFileInfo(Part part) {
        setName(part.getName());
        setLabel(part.getName());
        setSize(part.getSize());
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDeleteUrl() {
        return deleteUrl;
    }

    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }

    public String getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);

        path = o.getString("path", path);
        url = o.getString("url", url);
        thumbnail = o.getString("thumbnail", thumbnail);
        deleteUrl = o.getString("deleteUrl", deleteUrl);
        deleteType = o.getString("deleteType", deleteType);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        super.jsonOut(out, opts);
        out.entry("path", path);
        out.entry("url", url);
        out.entry("thumbnail", thumbnail);
        out.entry("deleteUrl", deleteUrl);
        out.entry("deleteType", deleteType);
    }

}
