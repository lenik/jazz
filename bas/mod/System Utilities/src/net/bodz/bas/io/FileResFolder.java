package net.bodz.bas.io;

import java.io.File;

public class FileResFolder implements ResFolder {

    private File    dir;
    private boolean autoMkdirs;

    public FileResFolder(File dir) {
        if (dir == null)
            throw new NullPointerException("dir");
        if (dir.exists()) {
            if (!dir.isDirectory())
                throw new IllegalStateException(
                        "Non-directory with the same path is already existed: "
                                + dir);
        }
        this.dir = dir;
    }

    public boolean isAutoMkdirs() {
        return autoMkdirs;
    }

    public void setAutoMkdirs(boolean autoMkdirs) {
        this.autoMkdirs = autoMkdirs;
    }

    @Override
    public FileResLink get(String path) {
        File file = new File(dir, path);
        FileResLink link = new FileResLink(file);
        link.setAutoMkdirs(autoMkdirs);
        return link;
    }

    @Override
    public int hashCode() {
        int hash = dir.hashCode();
        if (autoMkdirs)
            hash ^= 0x3755c28d;
        else
            hash ^= 0x002468b4;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FileResFolder) {
            FileResFolder a = (FileResFolder) obj;
            if (dir.equals(a.dir))
                return autoMkdirs == a.autoMkdirs;
        }
        return false;
    }

    @Override
    public String toString() {
        String s = dir.toString();
        if (autoMkdirs)
            s = "* " + s;
        return s;
    }

}
