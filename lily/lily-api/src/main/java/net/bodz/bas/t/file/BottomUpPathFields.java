package net.bodz.bas.t.file;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.string.PosRange;
import net.bodz.bas.c.string.StringStat;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.tuple.Split;

/**
 * Bottom: dir/name/extension
 *
 * Up: path/filename.
 */
public class BottomUpPathFields
        implements
            IPathFields {

    String dirName;
    String name; // without extension
    String extension;

    String _pathCache;
    String _fileNameCache;

    public BottomUpPathFields() {
    }

    public BottomUpPathFields(IPathFields o) {
        this.dirName = o.getDirName();
        this.name = o.getName();
        this.extension = o.getExtension();
        this._pathCache = o.getPath();
        this._fileNameCache = o.getFileName();
    }

    public BottomUpPathFields(BottomUpPathFields o) {
        this.dirName = o.dirName;
        this.name = o.name;
        this.extension = o.extension;
        this._pathCache = o._pathCache;
        this._fileNameCache = o._fileNameCache;
    }

    @Override
    public String getPath() {
        return _pathCache;
    }

    @Override
    public synchronized void setPath(String path) {
        if (Nullables.notEquals(this._pathCache, path)) {
            if (path == null) {
                dirName = name = extension = null;
                _pathCache = _fileNameCache = null;
            } else {
                Split dirBase = Split.dirBase(path);
                dirName = dirBase.a;
                _fileName(dirBase.b);
                this._pathCache = path;
            }
        }
    }

    @Override
    public synchronized void setPath(String dirName, String fileName) {
        if (Nullables.notEquals(this.dirName, dirName) //
                || Nullables.notEquals(this._fileNameCache, fileName)) {
            this.dirName = dirName;
            _fileName(fileName);
            updatePath();
        }
    }

    @Override
    public void setPath(String dirName, String name, String extension) {
        this.dirName = dirName;
        this.name = name;
        this.extension = extension;
        updateFileName();
    }

    void updatePath() {
        String path = _fileNameCache;
        if (dirName != null)
            path = dirName + "/" + _fileNameCache;
        this._pathCache = path;
    }

    @Override
    public String[] toArray() {
        String[] array = _pathCache.split("/");
        return array;
    }

    @Override
    public int length() {
        int slashCount = StringStat.count(_pathCache, '/');
        return slashCount;
    }

    @Override
    public String getField(int index) {
        PosRange range = Strings.selectToken(_pathCache, '/', index);
        if (range == null)
            return null;
        String token = _pathCache.substring(range.begin, range.end);
        return token;
    }

    @Override
    public void setField(int index, String field) {
        PosRange range = Strings.selectToken(_pathCache, '/', index);
        String left_ = _pathCache.substring(0, range.begin);
        String _right = _pathCache.substring(range.end);
        String rename = left_ + field + _right;
        this._pathCache = rename;
    }

    @Override
    public String getDirName() {
        return dirName;
    }

    @Override
    public synchronized void setDirName(String dirName) {
        if (Nullables.notEquals(this.dirName, dirName)) {
            this.dirName = dirName;
            updatePath();
        }
    }

    @Override
    public String getFileName() {
        return _fileNameCache;
    }

    @Override
    public synchronized void setFileName(String fileName) {
        _fileName(fileName);
        updatePath();
    }

    void _fileName(String fileName) {
        if (Nullables.equals(this._fileNameCache, fileName))
            return;
        Split split = Split.nameExtension(fileName);
        name = split.a;
        extension = split.b;
        this._fileNameCache = fileName;
    }

    void updateFileName() {
        String fileName = name;
        if (extension != null) {
            if (name == null)
                fileName = "." + extension;
            else
                fileName = name + "." + extension;
        }
        this._fileNameCache = fileName;
        updatePath();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public synchronized void setName(String name) {
        if (Nullables.notEquals(this.name, name)) {
            this.name = name;
            updateFileName();
        }
    }

    @Override
    public String getExtension() {
        return extension;
    }

    @Override
    public synchronized void setExtension(String extension) {
        if (Nullables.notEquals(this.extension, extension)) {
            this.extension = extension;
            updateFileName();
        }
    }

    @Override
    public String toString() {
        return _pathCache;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        String path = o.getString(K_PATH);
        if (path != null) {
            setPath(path);
        } else {
            String dirName = o.getString(K_DIR_NAME);
            String fileName = o.getString(K_FILE_NAME);
            if (fileName != null) {
                setPath(dirName, fileName);
            } else {
                String name = o.getString(K_NAME);
                String extension = o.getString(K_EXTENSION);
                setPath(dirName, name, extension);
            }
        }
    }

    @Override
    public void readObject(IElement o)
            throws ParseException, LoaderException {
        String path = o.a(K_PATH).getString();
        if (path != null) {
            setPath(path);
        } else {
            String dirName = o.a(K_DIR_NAME).getString();
            String fileName = o.a(K_FILE_NAME).getString();
            if (fileName != null) {
                setPath(dirName, fileName);
            } else {
                String name = o.a(K_NAME).getString();
                String extension = o.a(K_EXTENSION).getString();
                setPath(dirName, name, extension);
            }
        }
    }

}
