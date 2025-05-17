package net.bodz.bas.site.file;

import java.io.IOException;

import jakarta.servlet.http.Part;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class UploadedFileInfo
        extends ItemFile {

    String url;
    String deleteUrl;
    String deleteType = "DELETE";

    public UploadedFileInfo() {
    }

    public UploadedFileInfo(Part part) {
        setName(part.getName());
        setLabel(part.getName());
        setSize(part.getSize());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);
        url = o.getString("url", url);
        deleteUrl = o.getString("deleteUrl", deleteUrl);
        deleteType = o.getString("deleteType", deleteType);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        super.jsonOut(out, opts);
        out.entry("url", url);
        out.entry("deleteUrl", deleteUrl);
        out.entry("deleteType", deleteType);
    }

}
