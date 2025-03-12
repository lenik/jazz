package net.bodz.bas.codegen;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.t.tuple.Split;

public class MutableFileInfo
        implements
            IFileInfo {

    static final String FS = SysProps.fileSep;

    Path baseDir;
    String localDirectory;
    String fileName;

    public MutableFileInfo(Path baseDir, String localDirectory, String fileName) {
        if (fileName == null)
            throw new NullPointerException("fileName");
        this.baseDir = baseDir;
        this.localDirectory = localDirectory;
        this.fileName = fileName;
    }

    public MutableFileInfo(Path baseDir, String localPath) {
        if (localPath == null)
            throw new NullPointerException("localPath");
        this.baseDir = baseDir;
        setLocalPath(localPath);
    }

    @Override
    public Path getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(Path baseDir) {
        this.baseDir = baseDir;
    }

    @Override
    public String getLocalDirectory() {
        return localDirectory;
    }

    public void setLocalDirectory(String localDirectory) {
        this.localDirectory = localDirectory;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        if (fileName == null)
            throw new NullPointerException("fileName");
        this.fileName = fileName;
    }

    @Override
    public String getName() {
        return Split.nameExtension(fileName).a;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        String old = Split.nameExtension(fileName).b;
        if (old == null)
            fileName = name;
        else
            fileName = name + "." + old;
    }

    @Override
    public String getExtension() {
        return Split.nameExtension(fileName).b;
    }

    public void setExtension(String extension) {
        if (extension == null)
            fileName = extension;
        else
            fileName = Split.nameExtension(fileName).a + "." + extension;
    }

    @Override
    public String getDotExtension() {
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot == -1)
            return "";
        else
            return fileName.substring(lastDot);
    }

    public void setDotExtension(String dotExtension) {
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot == -1) {
            if (!Nullables.isEmpty(dotExtension))
                fileName += dotExtension;
        } else {
            String name = fileName.substring(0, lastDot);
            if (!Nullables.isEmpty(dotExtension))
                name += dotExtension;
            fileName = name;
        }
    }

    @Override
    public String getLocalPath() {
        if (localDirectory == null)
            return fileName;
        else
            return localDirectory + FS + fileName;
    }

    public void setLocalPath(String localPath) {
        if (localPath == null)
            throw new NullPointerException("localPath");
        Split split = Split.dirBase(localPath);
        localDirectory = split.a;
        fileName = split.b;
    }

    @Override
    public String getPath() {
        if (baseDir == null)
            return getLocalPath();
        else
            return baseDir + FS + getLocalPath();
    }

    @Override
    public File toFile() {
        String path = getPath();
        return new File(path);
    }

    @Override
    public Path toPath() {
        String path = getPath();
        return Paths.get(path);
    }

    @Override
    public String toString() {
        return baseDir + " :: " + localDirectory + " :: " + fileName;
    }

}
