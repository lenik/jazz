package net.bodz.lily.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import net.bodz.bas.data.codec.builtin.HexCodec;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.t.file.IPathFields;

public class LocalVolume
        extends AbstractVolume {

    Path baseDir;

    public LocalVolume(String id, IAnchor baseAnchor, Path baseDir) {
        super(id, baseAnchor);
        if (baseDir == null)
            throw new NullPointerException("baseDir");
        this.baseDir = baseDir;
    }

    public LocalVolume(String id, String baseWebPath, Path baseDir) {
        super(id, baseWebPath);
        if (baseDir == null)
            throw new NullPointerException("baseDir");
        this.baseDir = baseDir;
    }

    @Override
    public IVolumeItem getFile(IPathFields pathFields) {
        DefaultVolumeFile vf = new DefaultVolumeFile(this, pathFields);
        return vf;
    }

    @Override
    public Path getLocalDir() {
        return baseDir;
    }

    @Override
    public Path getLocalFile(String path) {
        if (path.startsWith("/"))
            path = path.substring(1);
        return baseDir.resolve(path);
    }

    @Override
    public boolean exists(String path) {
        Path file = getLocalFile(path);
        return Files.exists(file);
    }

    @Override
    public boolean isSymLink(String path) {
        Path file = getLocalFile(path);
        return Files.isSymbolicLink(file);
    }

    @Override
    public String getSymLinkTarget(String path)
            throws IOException {
        Path file = getLocalFile(path);
        Path target = Files.readSymbolicLink(file);
        return target.toString();
    }

    @Override
    public long getSize(String path)
            throws IOException {
        Path file = getLocalFile(path);
        return Files.size(file);
    }

    static final HexCodec hexCodec = new HexCodec("");

    @Override
    public String getSHA1(String path)
            throws IOException {
        Path file = getLocalFile(path);
        byte[] sha1 = ResFn.path(file).to(StreamReading.class).sha1();
        String sha1str = hexCodec.encode(sha1);
        return sha1str;
    }

    @Override
    public void renameTo(String path, String newFileName)
            throws IOException {
        Path file = getLocalFile(path);
        Path dir = file.getParent();
        Path dest = dir.resolve(newFileName);
        moveTo(file, dest);
    }

    @Override
    public void moveTo(String path, String newPath)
            throws IOException {
        Path file = getLocalFile(path);
        Path dest = getLocalFile(newPath);
        moveTo(file, dest);
    }

    @Override
    public void moveTo(String path, String newDirName, String newFileName)
            throws IOException {
        Path file = getLocalFile(path);

        String newPath = newFileName;
        if (newDirName != null) {
            while (newDirName.endsWith("/"))
                newDirName = newDirName.substring(0, newDirName.length() - 1);
            newPath = newDirName + "/" + newFileName;
        }
        Path dest = getLocalFile(newPath);

        moveTo(file, dest);
    }

    void moveTo(Path src, Path dest)
            throws IOException {
        Files.move(src, dest);
    }

    void moveTo(File src, File dest)
            throws IOException {
        src.renameTo(dest);
    }

    @Override
    public void delete(String path)
            throws IOException {
        Path file = getLocalFile(path);
        Files.delete(file);
    }

}
