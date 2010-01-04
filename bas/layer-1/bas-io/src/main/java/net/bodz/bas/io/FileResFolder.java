package net.bodz.bas.io;

import java.io.File;

public class FileResFolder implements ResFolder {

    private final File dir;
    private final boolean mkdirsWhenAccess;
    private final boolean mkdirsWhenWrite;

    public FileResFolder(File dir) {
        this(dir, false, false);
    }

    public FileResFolder(File dir, boolean autoMkdirs) {
        this(dir, false, true);
    }

    public FileResFolder(File dir, boolean mkdirsWhenAccess, boolean mkdirsWhenWrite) {
        if (dir == null)
            throw new NullPointerException("dir"); //$NON-NLS-1$
        if (dir.exists()) {
            if (!dir.isDirectory())
                throw new IllegalStateException(SysNLS.getString("FileResFolder.nondirWithSameName") + dir); //$NON-NLS-1$
        }
        this.dir = dir;
        this.mkdirsWhenAccess = mkdirsWhenAccess;
        this.mkdirsWhenWrite = mkdirsWhenWrite;
    }

    public File getFile() {
        return dir;
    }

    @Override
    public FileResLink get(String path) {
        File file = new File(dir, path);
        if (mkdirsWhenAccess) {
            File parentFile = file.getParentFile();
            if (parentFile != null)
                parentFile.mkdirs();
        }
        FileResLink link = new FileResLink(file, mkdirsWhenWrite);
        return link;
    }

    @Override
    public int hashCode() {
        int hash = 0x5bf62476 ^ dir.hashCode();
        if (mkdirsWhenAccess)
            hash ^= 0x3755c28d;
        if (mkdirsWhenWrite)
            hash ^= 0x002468b4;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FileResFolder) {
            FileResFolder a = (FileResFolder) obj;
            if (dir.equals(a.dir))
                return mkdirsWhenAccess == a.mkdirsWhenAccess && mkdirsWhenWrite == a.mkdirsWhenWrite;
        }
        return false;
    }

    @Override
    public String toString() {
        String s = dir.toString();
        if (mkdirsWhenAccess)
            s = "* " + s; //$NON-NLS-1$
        if (mkdirsWhenWrite)
            s = "* " + s; //$NON-NLS-1$
        return s;
    }

}
