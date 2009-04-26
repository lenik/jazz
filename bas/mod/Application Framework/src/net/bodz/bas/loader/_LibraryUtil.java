package net.bodz.bas.loader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class _LibraryUtil {

    static Set<String> loaded;
    static {
        loaded = new HashSet<String>();
    }

    public static synchronized void loadLibrary(String libname) {
        try {
            System.loadLibrary(libname);
        } catch (UnsatisfiedLinkError e) {
            String filename = System.mapLibraryName(libname);
            URL url = _LibraryUtil.class.getResource("/" + filename);
            if (url == null)
                throw e;
            if (loaded.contains(filename))
                return;
            File tmpFile = null;
            try {
                InputStream in = url.openStream();
                tmpFile = File.createTempFile(filename, null, true);
                FileOutputStream out = new FileOutputStream(tmpFile);
                byte[] block = new byte[4096];
                int n;
                while ((n = in.read(block)) != -1)
                    out.write(block, 0, n);
                out.close();
                in.close();
            } catch (IOException ex) {
                throw new RuntimeException("Can't copy library to " + tmpFile, ex);
            }
            System.load(tmpFile.getPath());
            loaded.add(filename);
        }
    }

}
