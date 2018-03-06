package net.bodz.bas.site.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.bodz.bas.http.ctx.IAnchor;
import net.bodz.bas.site.IBasicSiteAnchors;

/**
 *
 * Parameters:
 *
 * @param scheme
 *
 *            Check out <a href="https://blueimp.github.io/jQuery-File-Upload/">blueimp file
 *            upload</a>
 */
public class UploadHandler
        implements IBasicSiteAnchors {

    File localDir;
    IAnchor anchor;

    List<IUploadHandlerExtension> extensions = new ArrayList<>();

    /**
     * @param getPath
     *            The start part of the download href relative to the webapp. Should be terminated
     *            with file separator.
     */
    public UploadHandler(File localDir, String getPath) {
        this(localDir, _webApp_.join(getPath));
    }

    public UploadHandler(File localDir, IAnchor getAnchor) {
        if (localDir == null)
            throw new NullPointerException("localDir");
        if (!localDir.isDirectory())
            throw new IllegalArgumentException("Not a local directory: " + localDir);
        if (getAnchor == null)
            throw new NullPointerException("getAnchor");
        this.localDir = localDir;
        this.anchor = getAnchor;
    }

    public UploadResult handlePostRequest(HttpServletRequest request)
            throws IOException {
        if (!ServletFileUpload.isMultipartContent(request))
            throw new IllegalArgumentException("Request is not multipart.");

        ServletFileUpload uploader = new ServletFileUpload(new DiskFileItemFactory());

        UploadResult result = new UploadResult();
        try {
            List<FileItem> items = uploader.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField())
                    continue;

                if (item.getName().isEmpty())
                    throw new IllegalArgumentException("empty filename.");
                String itemPath = getItemPath(item.getName());
                File file = new File(localDir, itemPath);
                item.write(file);

                UploadedFileInfo fileInfo = new UploadedFileInfo(item);

                fileInfo.url = anchor.join(itemPath).absoluteHref();
                fileInfo.deleteUrl = fileInfo.url;

                // TODO image auto-scale...?
                fileInfo.thumbnail = fileInfo.url;

                result.add(fileInfo);
            }
            return result;
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    public String getItemPath(String itemName) {
        return itemName;
    }

}