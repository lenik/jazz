package net.bodz.bas.ant;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.bodz.bas.io.Files;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.resources.FileResource;

public class CounterTask extends Task {

    private String      propertyName;

    private File        file;
    private Set<File>   depends;
    private int         minDiffer = 1000; // 1 sec.

    private int         start     = 1;
    private int         increment = 1;
    public NumberFormat format;

    static NumberFormat defaultFormat;
    static {
        String defaultPattern = "#";
        defaultFormat = new DecimalFormat(defaultPattern);
    }

    public CounterTask() {
        depends = new HashSet<File>();
        format = defaultFormat;
    }

    public void setProperty(String property) {
        this.propertyName = property;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setParentFile(File parentFile) {
        depends.add(parentFile);
    }

    public void setFormat(String format) {
        this.format = new DecimalFormat(format);
    }

    public void setParentFiles(Reference fileSetRef) {
        Object _fileSet = fileSetRef.getReferencedObject();
        if (_fileSet instanceof FileSet) {
            FileSet fileSet = (FileSet) _fileSet;
            Iterator<?> iter = fileSet.iterator();
            while (iter.hasNext()) {
                FileResource fileRes = (FileResource) iter.next();
                File file = fileRes.getFile();
                depends.add(file);
            }
        } else {
            String id = fileSetRef.getRefId();
            throw new IllegalArgumentException(id + " isn't fileset");
        }
    }

    public void setMinDiffer(int minDiffer) {
        this.minDiffer = minDiffer;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    @Override
    public void execute() throws BuildException {
        if (file == null)
            throw new BuildException("counter file isn't specified");

        Project project = getProject();
        String verText;

        boolean reset = false;
        if (file.isFile()) {
            long fileVer = file.lastModified();
            for (File dep : depends) {
                long depVer = dep.lastModified();
                if (depVer - fileVer > minDiffer) {
                    reset = true;
                    break;
                }
            }
        }

        long ver = start;
        try {
            if (file.isFile() && !reset) {
                String text = Files.readAll(file);
                Number num = format.parse(text);
                ver = num.longValue();
                ver += increment;
            }
        } catch (IOException e) {
            throw new BuildException(e);
        } catch (ParseException e) {
            throw new BuildException(e);
        }

        verText = format.format(ver);
        try {
            Files.write(file, verText);
        } catch (IOException e) {
            throw new BuildException(e);
        }

        project.setUserProperty(propertyName, verText);
    }
}
