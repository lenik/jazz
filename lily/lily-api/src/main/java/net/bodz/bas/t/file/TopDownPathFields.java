package net.bodz.bas.t.file;

import java.io.File;

import net.bodz.bas.c.string.PosRange;
import net.bodz.bas.c.string.StringStat;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;

/**
 * Top: path/filename.
 *
 * down: dir/name/extension
 */
public class TopDownPathFields
        implements IPathFields {

    String path;

    public TopDownPathFields() {
    }

    public TopDownPathFields(IPathFields o) {
        this.path = o.getPath();
    }

    public TopDownPathFields(TopDownPathFields o) {
        this.path = o.path;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void setPath(String dirName, String fileName) {
        if (fileName == null)
            throw new NullPointerException("fileName");
        if (dirName == null)
            this.path = fileName;
        else
            this.path = dirName + File.separatorChar + fileName;
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
        if (path == null)
            return new String[0];
        String[] array = path.split(File.separator);
        return array;
    }

    @Override
    public int length() {
        if (path == null)
            return 0;
        int slashCount = StringStat.count(path, File.separator);
        return slashCount;
    }

    @Override
    public String getField(int index) {
        if (path == null)
            return null;
        PosRange range = Strings.selectToken(path, index, '/');
        if (range == null)
            return null;
        String token = path.substring(range.begin, range.end);
        return token;
    }

    @Override
    public void setField(int index, String field) {
        if (path == null)
            throw new IllegalStateException("path wan't init");
        PosRange range = Strings.selectToken(path, index, '/');
        if (range == null)
            throw new IndexOutOfBoundsException(index);
        String left_ = path.substring(0, range.begin);
        String _right = path.substring(range.end);
        String rename = left_ + field + _right;
        this.path = rename;
    }

    @Override
    public String getDirName() {
        if (path == null)
            return null;
        int lastSlash = path.lastIndexOf(File.separatorChar);
        if (lastSlash == -1)
            return null;
        else
            return path.substring(0, lastSlash);
    }

    @Override
    public void setDirName(String dirName) {
        String fileName = getFileName();
        setPath(dirName, fileName);
    }

    @Override
    public String getFileName() {
        if (path == null)
            return null;
        int lastSlash = path.lastIndexOf(File.separatorChar);
        if (lastSlash == -1)
            return path;
        else
            return path.substring(lastSlash + 1);
    }

    @Override
    public void setFileName(String fileName) {
        String dirName = getDirName();
        setPath(dirName, fileName);
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
        return path;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
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
