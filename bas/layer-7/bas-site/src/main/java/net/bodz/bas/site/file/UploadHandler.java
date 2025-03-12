package net.bodz.bas.site.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.data.codec.builtin.HexCodec;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.io.res.builtin.InputStreamSource;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.IBasicSiteAnchors;
import net.bodz.bas.t.tuple.Split;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VariantMaps;

/**
 * @see <a href="https://blueimp.github.io/jQuery-File-Upload/">blueimp file upload</a>
 */
public class UploadHandler
        implements
            IBasicSiteAnchors {

    Path localDir;
    IAnchor anchor;
    boolean renameSha1 = true;

    /**
     * @param absoluteHref
     *            The start part of the download href relative to the webapp. Should be terminated
     *            with file separator.
     */
    public UploadHandler(File localDir, String absoluteHref) {
        this(localDir.toPath(), absoluteHref);
    }

    public UploadHandler(Path localDir, String absoluteHref) {
        this(localDir, _webApp_.join(absoluteHref));
    }

    public UploadHandler(File localDir, IAnchor anchor) {
        this(localDir.toPath(), anchor);
    }

    public UploadHandler(Path localDir, IAnchor anchor) {
        if (localDir == null)
            throw new NullPointerException("localDir");
        if (!Files.isDirectory(localDir))
            throw new IllegalArgumentException("Not a local directory: " + localDir);
        if (anchor == null)
            throw new NullPointerException("anchor");
        this.localDir = localDir;
        this.anchor = anchor;
    }

    public UploadResult handlePostRequest(HttpServletRequest request)
            throws IOException, ServletException {
        IVariantMap<String> q = VariantMaps.fromRequest(request);

        String contentType = request.getContentType();
        if (contentType != null && contentType.startsWith("multipart/")) {
            MultipartConfigElement mce = new MultipartConfigElement("/xxx");
            request.setAttribute("org.eclipse.jetty.multipartConfig", mce);
        }

        // the max bytes to transfer in a second.
        int maxSpeed = q.getInt("maxSpeed", 0);

        UploadResult result = new UploadResult();
        for (Part part : request.getParts()) {
            String fileName = part.getSubmittedFileName();
            if (fileName == null) // other query parameter as a request part
                continue;

            if (fileName.isEmpty())
                throw new IllegalArgumentException("empty filename.");

            String safeName = safeName(fileName);
            Path localFile = localDir.resolve(safeName);

            long beginTime = System.currentTimeMillis();
            long totalSize = 0;
            try (OutputStream out = Files.newOutputStream(localFile)) {
                InputStream in = part.getInputStream();
                InputStreamSource source = new InputStreamSource(in);
                for (byte[] block : source.read().blocks()) {
                    totalSize += block.length;
                    out.write(block);

                    if (maxSpeed != 0) {
                        long duration = System.currentTimeMillis() - beginTime;
                        long expectDurationMillis = totalSize * 1000 / maxSpeed;
                        if (duration < expectDurationMillis)
                            try {
                                out.flush();
                                System.out.print(".");
                                Thread.sleep(expectDurationMillis - duration);
                            } catch (InterruptedException e) {
                                throw new ServletException(e);
                            }
                    }
                }
            }
            if (maxSpeed != 0)
                System.out.println();

            ArchiveResult archiveResult = archive(localFile);
            // localFile = archiveResult.archived;
            // String sha1Name = archiveResult.archived.getFileName().toString();

            UploadedFileInfo fileInfo = new UploadedFileInfo(part);
            fileInfo.setFile(localFile);
            // fileInfo.setDir(dirWithinSchema);
            fileInfo.setOrigName(fileName); // orig. upload name.
            fileInfo.setName(safeName);
            fileInfo.setSha1(archiveResult.sha1);

            Split nameExtension = Split.nameExtension(fileName);
            fileInfo.setLabel(nameExtension.a);

            fileInfo.url = anchor.join(safeName).toUriPath();

            // use the same URL to delete.
            // but when it is necessary, some guard strings may be applied.
            fileInfo.deleteUrl = fileInfo.url;

            result.add(fileInfo);
        }
        return result;
    }

    static String safeName(String name) {
        return name;
    }

    static final HexCodec hexCodec = new HexCodec("");

    ArchiveResult archive(Path src)
            throws IOException {
//        ResFn.path
        byte[] sha1 = ResFn.path(src).to(StreamReading.class).sha1();
        String sha1str = hexCodec.encode(sha1);

        Path parentDir = src.getParent();
        String dotExt = FilePath.getExtension(src.getFileName().toString(), true);

        Path sha1File = parentDir.resolve(sha1str + dotExt);

//        if (!file.renameTo(sha1File))
//            throw new IOException(String.format("Can't rename file %s to %s.", file, sha1File));
        Files.createLink(sha1File, src);

        return new ArchiveResult(src, sha1File, sha1str);
    }

}