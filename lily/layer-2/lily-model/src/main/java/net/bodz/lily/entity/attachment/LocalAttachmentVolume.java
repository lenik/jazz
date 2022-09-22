package net.bodz.lily.entity.attachment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import net.bodz.bas.data.codec.builtin.HexCodec;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.servlet.ctx.IAnchor;

public class LocalAttachmentVolume
        extends AbstractAttachmentVolume {

    File baseDir;

    public LocalAttachmentVolume(String id, IAnchor baseAnchor, File baseDir) {
        super(id, baseAnchor);
        if (baseDir == null)
            throw new NullPointerException("baseDir");
        this.baseDir = baseDir;
    }

    public LocalAttachmentVolume(String id, String baseWebPath, File baseDir) {
        super(id, baseWebPath);
        if (baseDir == null)
            throw new NullPointerException("baseDir");
        this.baseDir = baseDir;
    }

    @Override
    public IAttachment resolveAttachment(String path) {
        return new DefaultAttachment(this, path);
    }

    @Override
    public File getLocalFile(String path) {
        File file = new File(baseDir, path);
        return file;
    }

    @Override
    public boolean exists(String path) {
        File file = getLocalFile(path);
        return file.exists();
    }

    @Override
    public boolean isSymLink(String path) {
        File file = getLocalFile(path);
        return Files.isSymbolicLink(file.toPath());
    }

    @Override
    public String getSymLinkTarget(String path)
            throws IOException {
        File file = getLocalFile(path);
        Path target = Files.readSymbolicLink(file.toPath());
        return target.toString();
    }

    @Override
    public long getSize(String path)
            throws FileNotFoundException {
        File file = getLocalFile(path);
        return file.length();
    }

    static final HexCodec hexCodec = new HexCodec("");

    @Override
    public String getSHA1(String path)
            throws IOException {
        File file = getLocalFile(path);
        byte[] sha1 = new FileResource(file).to(StreamReading.class).sha1();
        String sha1str = hexCodec.encode(sha1);
        return sha1str;
    }

    @Override
    public boolean rename(String path, String newFileName)
            throws IOException {
        File file = getLocalFile(path);
        File dir = file.getParentFile();
        File dest = new File(dir, newFileName);
        return moveTo(file, dest);
    }

    @Override
    public boolean moveTo(String path, String newPath)
            throws IOException {
        File file = getLocalFile(path);
        File dest = getLocalFile(newPath);
        return moveTo(file, dest);
    }

    @Override
    public boolean moveTo(String path, String newDirName, String newFileName)
            throws IOException {
        File file = getLocalFile(path);

        String newPath = newFileName;
        if (newDirName != null) {
            while (newDirName.endsWith("/"))
                newDirName = newDirName.substring(0, newDirName.length() - 1);
            newPath = newDirName + "/" + newFileName;
        }
        File dest = getLocalFile(newPath);

        return moveTo(file, dest);
    }

    boolean moveTo(File src, File dest)
            throws IOException {
        if (src.renameTo(dest))
            return true;
        return false;
    }

    @Override
    public boolean delete(String path)
            throws IOException {
        File file = getLocalFile(path);
        return file.delete();
    }

}
