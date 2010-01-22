package net.bodz.bas.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.bodz.bas.closure.alt.Pred2;
import net.bodz.bas.exceptions.IllegalArgumentTypeException;
import net.bodz.bas.exceptions.UnexpectedException;
import net.bodz.bas.fs.IFile;
import net.bodz.bas.fs.IFolder;
import net.bodz.bas.fs.PlainFile;
import net.bodz.bas.fs.URLFile;
import net.bodz.bas.fs.ZippedFolder;
import net.bodz.bas.fs.preparation.IStreamWritePreparation;
import net.bodz.bas.io.FilesTest;
import net.bodz.bas.text.diff.DiffComparator;
import net.bodz.bas.text.diff.DiffInfo;

/**
 * @test {@link FilesTest}
 */
public class Files {

    public static int blockSize = 4096;
    private static String slash;
    static {
        slash = System.getProperty("file.separator");
        if (slash == null)
            slash = "/";
    }

    static void _mkdir_p(File file) {
        if (file == null)
            throw new NullPointerException("fille");
        File parentFile = file.getParentFile();
        parentFile = Files.canoniOf(parentFile);
        parentFile.mkdirs();
    }

    // Path functions

    /**
     * This canonical form is safe to getParentFile().
     */
    public static File canoniOf(File f) {
        if (f == null)
            return null;
        try {
            return f.getCanonicalFile();
        } catch (Exception e) {
            return f.getAbsoluteFile();
        }
    }

    /**
     * @see CWD#get(String)
     */
    public static File canoniOf(String path)
            throws NullPointerException {
        return canoniOf(new File(path));
    }

    public static File canoniOf(URI uri)
            throws NullPointerException {
        if (uri == null)
            throw new NullPointerException();
        return canoniOf(new File(uri));
    }

    public static File canoniOf(URL url)
            throws NullPointerException {
        if (url == null)
            throw new NullPointerException();
        try {
            return canoniOf(url.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public static File canoniOf(Object o) {
        if (o == null)
            return null;
        if (o instanceof File)
            return canoniOf((File) o);
        if (o instanceof String)
            return canoniOf((String) o);
        if (o instanceof URI)
            return canoniOf((URI) o);
        if (o instanceof URL)
            return canoniOf((URL) o);
        throw new IllegalArgumentTypeException(o);
    }

    public static File canoniOf(Object parent, String child) {
        File _parent = canoniOf(parent);
        return canoniOf(new File(_parent, child));
    }

    static File getJarFile(URL jarURL) {
        assert "jar".equals(jarURL.getProtocol());
        String s = jarURL.getPath(); // path = file:/...!...
        assert s.startsWith("file:");
        int excl = s.lastIndexOf('!');
        if (excl != -1) // assert
            s = s.substring(0, excl); // path = file:/...
        try {
            URL truncatedURL = new URL(s);
            return canoniOf(truncatedURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * if url is an entry of a jar file, then the jar file is returned.
     * 
     * @return the accessible file part of url
     */
    public static File getFile(URL url)
            throws MalformedURLException {
        if (url == null)
            return null;
        String protocol = url.getProtocol();
        if ("jar".equals(protocol))
            return getJarFile(url);
        return canoniOf(url);
    }

    public static IFolder toFolder(URL url, String removeSubPath)
            throws IOException {
        if (removeSubPath == null || removeSubPath.isEmpty())
            return new URLFile(url);
        // jar:file:/C:/abc/dir/example.jar!/com/example/Name.class
        // String _url = url.toExternalForm();

        // s=file:/C:/abc/dir/example.jar!/com/example/Name.class
        String s = url.toExternalForm();
        if (!s.endsWith(removeSubPath)) {
            throw new IllegalArgumentException(String.format("URL isn\'t end with %s: %s", removeSubPath, url));
        }
        int rlen = removeSubPath.length();
        // s=file:/C:/abc/dir/example.jar!/, or file:/C:/abc/dir/
        s = s.substring(0, s.length() - rlen);

        String protocol = url.getProtocol();
        if ("jar".equals(protocol)) {
            // the jar URL is verified by above. so now can safely return.
            File jarFile = getJarFile(url);
            IFile plainJarFile = new PlainFile(jarFile);
            return new ZippedFolder(plainJarFile);
        }
        URL truncatedURL = new URL(s);
        File dir = canoniOf(truncatedURL);
        return new PlainFile(dir);
    }

    private static File TMPDIR;

    static {
        File t;
        String TEMP;
        if ((TEMP = System.getenv("TEMP")) != null)
            t = Files.canoniOf(TEMP);
        else if ((TEMP = System.getenv("TMP")) != null)
            t = Files.canoniOf(TEMP);
        else
            t = Files.canoniOf("/tmp");
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
    public static File getTmpDir() {
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
        return convertToFile(key, in, "CTF-" + name, ".tmp", TMPDIR);
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

    public static String getRelativeName(File file, File start) {
        if (start == null)
            throw new NullPointerException("start");
        file = Files.canoniOf(file);
        start = Files.canoniOf(start);
        List<String> tails = new ArrayList<String>();
        for (File look = file;; look = look.getParentFile()) {
            if (look == null)
                throw new UnexpectedException(String.format("File %s isn\'t in the start dir %s", file, start));
            if (look.equals(start))
                break;
            tails.add(look.getName());
        }
        StringBuffer buffer = null;
        for (int i = tails.size() - 1; i >= 0; i--) {
            if (buffer == null)
                buffer = new StringBuffer(tails.size() * 16);
            else
                buffer.append(slash);
            buffer.append(tails.get(i));
        }
        if (buffer == null)
            return "";
        return buffer.toString();
    }

    public static File getAbsoluteFile(File start, String relativeName) {
        if (relativeName == null || relativeName.isEmpty())
            return start;
        return Files.canoniOf(start, relativeName);
    }

    public static File findBase(File... files) {
        if (files.length == 0)
            return null;
        File base = files[0];
        for (int i = 1; i < files.length; i++) {
            base = findBase(base, files[i]);
            if (base == null)
                return null;
        }
        return base;
    }

    public static File findBase(File a, File b) {
        if (a == null || b == null)
            return null;
        if (a.equals(b))
            return a;
        final File _a = a.getParentFile();
        for (File i = a; i != null; i = i.getParentFile())
            if (i.equals(b))
                return b;
        final File _b = b.getParentFile();
        for (File i = b; i != null; i = i.getParentFile())
            if (i.equals(a))
                return a;
        return findBase(_a, _b);
    }

    // File system operations

    public static boolean deleteTree(File start) {
        assert start != null;
        if (!start.exists())
            return false;
        if (start.isFile())
            return start.delete();
        assert start.isDirectory();
        File[] children = start.listFiles();
        boolean succ = true;
        for (File child : children) {
            succ = deleteTree(child) && succ;
        }
        succ = start.delete() && succ;
        return succ;
    }

    public static boolean move(File src, File dst, boolean force)
            throws IOException {
        if (dst.exists())
            if (force) {
                if (dst.delete())
                    return move(src, dst, false);
                throw new IOException("Can\'t delete the existed file " + dst);
            } else
                throw new IOException(String.format("Destination %s is already existed", dst));
        if (src.renameTo(dst))
            return true;
        if (copy(src, dst))
            return src.delete();
        throw new IOException(String.format("Failed to copy %s to %s", src, dst));
    }

    public static boolean move(File src, File dst)
            throws IOException {
        return move(src, dst, false);
    }

    public static boolean copy(IFile src, IFile dst, boolean append)
            throws IOException {
        assert src != null : "null src";
        assert dst != null : "null dst";

        InputStream in = src.forRead().newInputStream();

        IStreamWritePreparation writePrep = dst.forWrite();
        writePrep.setAppendMode(append);
        OutputStream out = writePrep.newOutputStream();

        try {
            byte[] buf = new byte[blockSize];
            int len;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
        } finally {
            in.close();
            out.close();
        }
        return true;
    }

    public static boolean copy(IFile src, IFile dst)
            throws IOException {
        return copy(src, dst, false);
    }

    /**
     * @return first position of difference, or -1 if the two are same.
     */
    public static long diff_1(Object src, Object dst)
            throws IOException {
        if (src == dst)
            return -1;
        if (src == null || dst == null)
            return 0;
        if (src instanceof String)
            src = new File((String) src);
        if (dst instanceof String)
            dst = new File((String) dst);
        File srcf = null;
        File dstf = null;
        if (src instanceof File)
            if (!(srcf = ((File) src)).canRead())
                return 0;
        if (dst instanceof File)
            if (!(dstf = ((File) dst)).canRead())
                return 0;
        if (srcf != null && dstf != null) {
            if (srcf.length() != dstf.length())
                return 0;
        }
        boolean closeSrc = shouldClose(src);
        boolean closeDst = shouldClose(dst);
        InputStream s = getInputStream(src);
        InputStream d = null;
        try {
            d = getInputStream(dst);
            byte[] sbuf = new byte[blockSize];
            byte[] dbuf = new byte[blockSize];
            long offset = 0;
            int slen;
            while ((slen = s.read(sbuf)) != -1) {
                int dlen = d.read(dbuf, 0, slen);
                if (dlen == -1)
                    return offset;
                while (dlen != slen) {
                    int dmore = d.read(dbuf, dlen, slen - dlen);
                    if (dmore == -1)
                        return offset + dlen;
                    dlen += dmore;
                }
                assert slen == dlen;
                if (slen == sbuf.length)
                    if (Arrays.equals(sbuf, dbuf)) {
                        offset += slen;
                        continue;
                    }
                for (int i = 0; i < slen; i++)
                    if (sbuf[i] != dbuf[i])
                        return offset + i;
                offset += slen;
            }
        } finally {
            if (closeSrc)
                s.close();
            if (closeDst && d != null)
                d.close();
        }
        return -1;
    }

    public static boolean equals(Object src, Object dst)
            throws IOException {
        return diff_1(src, dst) == -1;
    }

    /**
     * if difference info is available, then return the first difference. otherwise return
     * <code>false</code> if any different exists.
     * 
     * @return <code>null</code> if the same
     */
    public static Object copyDiff(IFile src, IFile dst, DiffComparator diff)
            throws IOException {
        Object ret;
        if (diff != null) {
            List<String> al = src.forRead().listLines();
            List<String> bl = dst.forRead().listLines();
            List<DiffInfo> diffs = diff.diffCompare(al, bl);
            if (diffs.size() == 0)
                return null;
            ret = diffs;
        } else {
            if (equals(src, dst))
                return null;
            ret = false;
        }
        // Writer out = dst.forWrite().newWriter();
        write(dst, src);
        return ret;
    }

    /**
     * return <code>true</code> if the two are diff and actually copied.
     */
    public static boolean copyDiff(Object src, Object dst)
            throws IOException {
        return copyDiff(src, dst, null) != null;
    }

    /**
     * @param inputs
     *            each input may be:
     *            <ul>
     *            <li>File inputFile
     *            <li>String inputFileName
     *            <li>Predicate2(output, File[] inputs) rule
     *            </ul>
     */
    @SuppressWarnings("unchecked")
    public static boolean make(File output, Object... inputs) {
        assert output != null;
        output = Files.canoniOf(output);
        File outd = output.getParentFile();
        List<File> files = new ArrayList<File>();
        long outl = output.exists() ? output.lastModified() : 0l;
        long mostRecent = outl;
        boolean succeeded = true;
        for (Object input : inputs) {
            assert input != null;
            File inputf;
            if (input instanceof File)
                inputf = (File) input;
            else if (input instanceof String)
                inputf = Files.canoniOf(outd, (String) input);
            else if (input instanceof Pred2) {
                if (mostRecent > outl) {
                    File[] finputs = files.toArray(new File[0]);
                    Pred2<File, File[]> ruledef = (Pred2<File, File[]>) input;
                    boolean succ = ruledef.eval(output, finputs);
                    succeeded = succeeded && succ;
                }
                files.clear();
                continue;
            } else
                throw new IllegalArgumentTypeException(input);
            if (!inputf.exists())
                throw new IllegalArgumentException("input file isn\'t existed: " + inputf);
            files.add(inputf);
            if (inputf.lastModified() > mostRecent)
                mostRecent = inputf.lastModified();
        }
        return succeeded;
    }

    /**
     * @return <code>null</code> if no match.
     */
    public static List<File> find(String wildPath) {
        List<File> found = new ArrayList<File>();
        find(wildPath, found);
        if (found.size() == 0)
            return null;
        return found;
    }

    static void find(String wildPath, Collection<File> found) {
        int ast = wildPath.indexOf('*');
        if (ast == -1) {
            File f = new File(wildPath);
            if (f.exists())
                found.add(f);
        } else {
            int estart = wildPath.lastIndexOf('/', ast);
            if (estart == -1)
                throw new IllegalArgumentException("invalid wild exp: " + wildPath);
            estart++;
            int eend = wildPath.indexOf('/', ast);
            if (eend == -1)
                eend = wildPath.length();
            String elm = wildPath.substring(estart, eend);
            String regex = elm;
            regex = regex.replace(".", "\\.");
            regex = regex.replace("*", ".*");
            Pattern p = Pattern.compile("^" + regex + "$");
            File parent = new File(wildPath.substring(0, estart));
            if (!parent.isDirectory()) {
                // maybe file or non-exist.
                // usually occured when multiple-* scan.
                return;
            }
            for (File f : parent.listFiles()) {
                String fname = f.getName();
                if (p.matcher(fname).matches()) {
                    String it = f.getPath() + wildPath.substring(eend);
                    find(it, found);
                }
            }
        }
    }

    static File[] sysPaths;
    static String[] sysExts;
    static {
        String ps = System.getProperty("path.separator");
        if (ps == null)
            ps = ":";
        String pathenv = System.getenv("PATH");
        if (pathenv == null)
            sysPaths = new File[0];
        else {
            String[] v = pathenv.split(ps);
            sysPaths = new File[v.length];
            for (int i = 0; i < v.length; i++)
                sysPaths[i] = new File(v[i]);
        }

        String pathextenv = System.getenv("PATHEXT");
        if (pathextenv == null)
            sysExts = new String[0];
        else {
            String[] v = pathextenv.split(ps);
            // sysExts = new String[v.length];
            // for (int i = 0; i < v.length; i++)
            // sysExts[i] = "." + v[i];
            sysExts = v;
        }
    }

    /**
     * Find program using system default PATHEXT (win32 only).
     * 
     * @return <code>null</code> if couldn't find name.
     */
    public static File which(String name, File... paths) {
        return which(name, sysExts, paths);
    }

    /**
     * @return <code>null</code> if couldn't find name.
     */
    public static File which(String name, String[] pathExts, File... paths) {
        if (paths == null || paths.length == 0)
            paths = sysPaths;
        for (File path : paths) {
            File f = new File(path, name);
            if (f.isFile())
                return f;
            if (pathExts != null)
                for (String ext : pathExts) {
                    f = new File(path, name + ext);
                    if (f.isFile())
                        return f;
                }
        }
        return null;
    }

}
