package net.bodz.bas.vfs.util;

import org.junit.Assert;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;

public class FileFinderTest
        extends Assert {

    public static void main(String[] args) {
        IFile start = VFS.resolve("/tmp");
        FileFinder finder = new FileFinder(start);
        finder.setMaxDepth(1000);
        finder.addFileFoundListener(new Listener1());
        for (IFile f : finder) {
            System.out.println(f.getPath());
        }
    }

    static class Listener1
            implements IFileFoundListener {

        @Override
        public void fileFound(FileFoundEvent event) {
            String name = event.getFile().getName();
            if (name.endsWith(".log"))
                event.setCanceled(true);
        }

    }

}
