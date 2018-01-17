package net.bodz.bas.site.file;

import org.apache.commons.fileupload.FileItem;

import net.bodz.bas.fmt.json.JsonSupport;

public class UploadedFileInfo
        extends JsonSupport {

    public String name;
    public String path;
    public long size;
    public String url;
    public String thumbnail;
    public String deleteUrl;
    public String deleteType = "DELETE";

    public UploadedFileInfo(FileItem item) {
        name = item.getName();
        size = item.getSize();
    }

}
