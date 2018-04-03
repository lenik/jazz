package net.bodz.bas.site.file;

import org.apache.commons.fileupload.FileItem;

public class UploadedFileInfo
        extends ItemFile {

    String path;
    String url;
    String thumbnail;
    String deleteUrl;
    String deleteType = "DELETE";

    public UploadedFileInfo(FileItem item) {
        setName(item.getName());
        setSize(item.getSize());
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

}
