package net.bodz.bas.site.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.data.codec.builtin.HexCodec;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.io.res.builtin.InputStreamSource;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.IBasicSiteAnchors;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

/**
 *
 * Parameters:
 *
 * @param scheme
 *
 *            Check out <a href="https://blueimp.github.io/jQuery-File-Upload/">blueimp file upload</a>
 */
public class UploadHandler
        implements
            IBasicSiteAnchors {

    File localDir;
    IAnchor anchor;
    boolean renameSha1 = true;

    /**
     * @param absoluteHref
     *            The start part of the download href relative to the webapp. Should be terminated with file separator.
     */
    public UploadHandler(File localDir, String absoluteHref) {
        this(localDir, _webApp_.join(absoluteHref));
    }

    public UploadHandler(File localDir, IAnchor anchor) {
        if (localDir == null)
            throw new NullPointerException("localDir");
        if (!localDir.isDirectory())
            throw new IllegalArgumentException("Not a local directory: " + localDir);
        if (anchor == null)
            throw new NullPointerException("anchor");
        this.localDir = localDir;
        this.anchor = anchor;
    }

    public UploadResult handlePostRequest(HttpServletRequest request)
            throws IOException, ServletException {

        String contentType = request.getContentType();
        if (contentType != null && contentType.startsWith("multipart/")) {
            MultipartConfigElement mce = new MultipartConfigElement("/xxx");
            request.setAttribute("org.eclipse.jetty.multipartConfig", mce);
        }

        UploadResult result = new UploadResult();
        for (Part part : request.getParts()) {
            String fileName = part.getSubmittedFileName();
            if (fileName == null) // other query parameter as a request part
                continue;

            if (fileName.isEmpty())
                throw new IllegalArgumentException("empty filename.");

            File localFile = new File(localDir, fileName);

            try (FileOutputStream out = new FileOutputStream(localFile)) {
                InputStream in = part.getInputStream();
                for (byte[] block : new InputStreamSource(in).read().blocks()) {
                    out.write(block);
                }
            }

            RenameResult renameResult = renameToSha1(localFile);

            UploadedFileInfo fileInfo = new UploadedFileInfo(part);
            fileInfo.setFile(localFile);
            fileInfo.setSha1(renameResult.sha1);
            // fileInfo.setDir(dirWithinSchema);

            fileInfo.url = anchor.join(renameResult.newFile.getName()).toUriPath();
            fileInfo.deleteUrl = fileInfo.url;

            // TODO image auto-scale...?
            fileInfo.thumbnail = fileInfo.url;

            result.add(fileInfo);
        }
        return result;
    }

    static final HexCodec hexCodec = new HexCodec("");

    RenameResult renameToSha1(File file)
            throws IOException {
        byte[] sha1 = ResFn.file(file).to(StreamReading.class).sha1();
        String sha1str = hexCodec.encode(sha1);

        File dir = file.getParentFile();
        String dotExt = FilePath.getExtension(file, true);

        File newName = new File(dir, sha1str + dotExt);
        if (!file.renameTo(newName))
            throw new IOException(String.format("Can't rename file %s to %s.", //
                    file, newName));

        return new RenameResult(file, newName, sha1str);
    }

}