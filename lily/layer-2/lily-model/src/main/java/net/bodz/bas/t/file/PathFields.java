package net.bodz.bas.t.file;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.tuple.Split;

public class PathFields
        implements
            IPathFields {

    String _path;
    String dirName;
    String _fileName;
    String name;
    String extension;

    public PathFields() {
    }

    public PathFields(PathFields o) {
        this._path = o._path;
        this.dirName = o.dirName;
        this._fileName = o._fileName;
        this.name = o.name;
        this.extension = o.extension;
    }

    public String getPath() {
        return _path;
    }

    public synchronized void setPath(String path) {
        if (Nullables.notEquals(this._path, path)) {
            if (path == null) {
                _path = dirName = _fileName = name = extension = null;
            } else {
                Split dirBase = Split.dirBase(path);
                dirName = dirBase.a;
                _fileName(dirBase.b);
                _path = path;
            }
        }
    }

    public synchronized void setPath(String dirName, String fileName) {
        if (Nullables.notEquals(this.dirName, dirName) //
                || Nullables.notEquals(this._fileName, fileName)) {
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
        String path = _fileName;
        if (dirName != null)
            path = dirName + "/" + _fileName;
        this._path = path;
    }

    public String getDirName() {
        return dirName;
    }

    public synchronized void setDirName(String dirName) {
        if (Nullables.notEquals(this.dirName, dirName)) {
            this.dirName = dirName;
            updatePath();
        }
    }

    public String getFileName() {
        return _fileName;
    }

    public synchronized void setFileName(String fileName) {
        _fileName(fileName);
        updatePath();
    }

    void _fileName(String fileName) {
        if (Nullables.equals(this._fileName, fileName))
            return;
        Split split = Split.nameExtension(fileName);
        name = split.a;
        extension = split.b;
        this._fileName = fileName;
    }

    void updateFileName() {
        String fileName = name;
        if (extension != null) {
            if (name == null)
                fileName = "." + extension;
            else
                fileName = name + "." + extension;
        }
        this._fileName = fileName;
        updatePath();
    }

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        if (Nullables.notEquals(this.name, name)) {
            this.name = name;
            updateFileName();
        }
    }

    public String getExtension() {
        return extension;
    }

    public synchronized void setExtension(String extension) {
        if (Nullables.notEquals(this.extension, extension)) {
            this.extension = extension;
            updateFileName();
        }
    }

    @Override
    public String toString() {
        return _path;
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
