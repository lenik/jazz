package net.bodz.bas.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.tools.FileObject;

public class FileTemp {

    static FileObject TMPDIR;
    static {
        FileObject t;
        String TEMP;
        if ((TEMP = System.getenv("TEMP")) != null)
            t = FilePath.canoniOf(TEMP);
        else if ((TEMP = System.getenv("TMP")) != null)
            t = FilePath.canoniOf(TEMP);
        else
            t = FilePath.canoniOf("/tmp");
        if (t.exists()) {
            if (!t.isDirectory())
                throw new RuntimeException("not a directory: " + t);
        } else
            t.mkdirs();
        TMPDIR = t;
    }

    /**
     * @see File#createTempFile(String, String)
     * @see File#createTempFile(String, String, File)
     */
    public static FileObject getTmpDir() {
        return TMPDIR;
    }

    static Map<Object, File> temps;
    static Pattern invalidFilenameChars;
    static {
        invalidFilenameChars = Pattern.compile("[^a-zA-Z0-9-_]");
    }

    public static File convertToFile(Object key, Object in)
            throws IOException {
        String name = String.valueOf(key);
        name = invalidFilenameChars.matcher(name).replaceAll("_");
        return convertToFile(key, in, "CTF-" + name, ".tmp", FileTemp.TMPDIR);
    }

    public static File convertToFile(Object key, Object in, String prefix, String suffix, File tmpdir)
            throws IOException {
        if (temps == null)
            temps = new HashMap<Object, File>();
        if (key != null) {
            File file = temps.get(key);
            if (file != null)
                return file;
        }
        File tmpFile = File.createTempFile(prefix, suffix, tmpdir);
        try {
            FileOutputStream out = new FileOutputStream(tmpFile);
            for (byte[] block : readByBlock(in)) {
                out.write(block);
            }
            out.close();
            tmpFile.deleteOnExit();
            if (key != null)
                temps.put(key, tmpFile);
            return tmpFile;
        } catch (IOException e) {
            tmpFile.delete();
            throw e;
        }
    }

}
