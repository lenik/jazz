package net.bodz.lily.gen.model.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.impl.TreeOutImpl;

public class Names {

    File baseDir;
    File startDir;
    List<String> subDirs = new ArrayList<>();
    String extension;
    boolean overwrite;

    String lastHref;

    public Names() {
    }

    public Names(File base) {
        this.baseDir = base;
    }

    @Override
    public Names clone() {
        Names o = new Names();
        o.baseDir = baseDir;
        o.startDir = startDir;
        o.subDirs = new ArrayList<>(subDirs);
        o.extension = extension;
        o.overwrite = overwrite;
        return o;
    }

    public Names start(File start) {
        Names o = clone();
        o.startDir = start;
        return o;
    }

    public Names subdir(String subdir) {
        Names o = clone();
        o.subDirs.add(subdir);
        return o;
    }

    public Names extension(String extension) {
        Names o = clone();
        o.extension = extension;
        return o;
    }

    public Names overwrite() {
        Names o = clone();
        o.overwrite = true;
        return o;
    }

    public File compose() {
        File file = startDir;
        for (String d : subDirs)
            file = new File(file, d);
        return file;
    }

    public ITreeOut open()
            throws FileNotFoundException {
        return open(false);
    }

    public ITreeOut open(boolean overwrite)
            throws FileNotFoundException {
        return open(compose(), overwrite);
    }

    public ITreeOut open(String name)
            throws FileNotFoundException {
        return open(name, false);
    }

    public ITreeOut open(String name, boolean overwrite)
            throws FileNotFoundException {
        File file = new File(compose(), name);
        File dir = file.getParentFile();
        if (!dir.exists())
            dir.mkdirs();
        return open(file, overwrite);
    }

    public ITreeOut open(File file)
            throws FileNotFoundException {
        return open(file, false);
    }

    public ITreeOut open(File file, boolean overwrite)
            throws FileNotFoundException {
        if (file.exists())
            if (!overwrite)
                return null;
        lastHref = FilePath.getRelativePath(file, baseDir);
        FileOutputStream fileOut = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fileOut, Charsets.UTF8);
        WriterPrintOut wpo = new WriterPrintOut(osw);
        return TreeOutImpl.from(wpo);
    }

}
