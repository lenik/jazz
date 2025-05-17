package net.bodz.bas.t.file;

import java.io.File;

import org.apache.commons.text.StringTokenizer;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.c.string.StringStat;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class ArrayPathFields
        implements
            IPathFields {

    String[] names;

    public ArrayPathFields(String... names) {
        this.names = names;
    }

    public ArrayPathFields(IPathFields o) {
        this.names = o.toArray();
    }

    public ArrayPathFields(ArrayPathFields o) {
        this.names = o.names;
    }

    static String[] split(String s, char sep) {
        return split(s, sep, 0);
    }

    static String[] split(String s, char sep, int add) {
        int n = StringStat.count(s, sep) + add;
        String[] v = new String[n];
        StringTokenizer tokens = new StringTokenizer(s, sep);
        int i = 0;
        while (tokens.hasNext()) {
            v[i++] = tokens.nextToken();
        }
        return v;
    }

    @Override
    public String getPath() {
        return StringArray.join(File.separator, names);
    }

    @Override
    public void setPath(String path) {
        this.names = split(path, File.separatorChar);
    }

    @Override
    public void setPath(String dirName, String fileName) {
        if (fileName == null)
            throw new NullPointerException("fileName");
        if (dirName == null)
            this.names = new String[] { fileName };
        else {
            String[] v = split(dirName, File.separatorChar, 1);
            v[v.length - 1] = fileName;
            this.names = v;
        }
    }

    @Override
    public void setPath(String dirName, String name, String extension) {
        String fileName = name;
        if (extension != null)
            fileName += "." + extension;
        setPath(dirName, fileName);
    }

    @Override
    public String[] toArray() {
        return names;
    }

    @Override
    public int length() {
        return names.length;
    }

    @Override
    public String getField(int index) {
        return names[index];
    }

    @Override
    public void setField(int index, String field) {
        this.names[index] = field;
    }

    @Override
    public String getDirName() {
        int dn = names.length - 1;
        StringBuilder sb = new StringBuilder(40 * dn);
        for (int i = 0; i < dn; i++) {
            if (i != 0)
                sb.append(File.separatorChar);
            sb.append(names[i]);
        }
        if (sb.length() == 0)
            return null;
        String dirName = sb.toString();
        return dirName;
    }

    @Override
    public void setDirName(String dirName) {
        String fileName = getFileName();
        setPath(dirName, fileName);
    }

    @Override
    public String getFileName() {
        if (names.length == 0)
            return null;
        return names[names.length - 1];
    }

    @Override
    public void setFileName(String fileName) {
        int n = names.length;
        if (n == 0)
            names = new String[] { fileName };
        else
            names[n - 1] = fileName;
    }

    @Override
    public String getName() {
        String fileName = getFileName();
        if (fileName == null)
            return null;
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot == -1)
            return fileName;
        else
            return fileName.substring(0, lastDot);
    }

    @Override
    public void setName(String name) {
        String extension = getExtension();
        String fileName = name;
        if (extension != null)
            fileName += "." + extension;
        String dirName = getDirName();
        setPath(dirName, fileName);
    }

    @Override
    public String getExtension() {
        String fileName = getFileName();
        if (fileName == null)
            return null;
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot == -1)
            return null;
        else
            return fileName.substring(lastDot + 1);
    }

    @Override
    public void setExtension(String extension) {
        String fileName = getName();
        if (extension != null)
            fileName += "." + extension;
        String dirName = getDirName();
        setPath(dirName, fileName);
    }

    @Override
    public String toString() {
        return StringArray.join(File.separator, names);
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        String path = o.getString(K_PATH);
        if (path != null) {
            setPath(path);
        }
    }

    @Override
    public void readObject(IElement o)
            throws ParseException, LoaderException {
        String path = o.a(K_PATH).getString();
        if (path != null) {
            setPath(path);
        }
    }

}
