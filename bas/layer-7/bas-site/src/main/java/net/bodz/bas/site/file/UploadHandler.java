package net.bodz.bas.site.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.http.ctx.IAnchor;

/**
 * 
 * Parameters:
 * 
 * @param scheme
 * 
 *            Check out <a href="https://blueimp.github.io/jQuery-File-Upload/">blueimp file
 *            upload</a>
 */
public class UploadHandler {

    IFilePathMapping mapping;

    public UploadHandler() {
    }

    public UploadHandler(IFilePathMapping mapping) {
        if (mapping == null)
            throw new NullPointerException("mapping");
        this.mapping = mapping;
    }

    public UploadResult handlePostRequest(HttpServletRequest request)
            throws IOException {
        IFilePathMapping mapping = this.mapping;
        if (mapping == null)
            mapping = (IFilePathMapping) request.getServletContext().getAttribute(IFilePathMapping.ATTRIBUTE_KEY);

        if (!ServletFileUpload.isMultipartContent(request))
            throw new IllegalArgumentException("Request is not multipart.");

        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());

        UploadResult result = new UploadResult();
        String schema = request.getParameter("schema");
        String subDir = request.getParameter("dir");

        File localDir;
        IAnchor anchor;
        if (schema == null) {
            localDir = mapping.getLocalRoot(request);
            anchor = mapping.getRootAnchor();
        } else {
            localDir = mapping.getLocalDir(request, schema);
            anchor = mapping.getAnchor(schema + "/");
        }

        if (subDir != null) {
            if (!subDir.endsWith(SysProps.fileSep))
                subDir += SysProps.fileSep;
            localDir = new File(localDir, subDir);
            anchor = anchor.join(subDir);
        }
        localDir.mkdirs();

        try {
            List<FileItem> items = uploadHandler.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField())
                    continue;

                if (item.getName().isEmpty())
                    throw new IllegalArgumentException("empty filename.");
                File file = new File(localDir, item.getName());
                item.write(file);

                UploadedFileInfo fileInfo = new UploadedFileInfo(item);
                fileInfo.url = anchor.join(fileInfo.name).absoluteHref();
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

}