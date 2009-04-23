package net.bodz.bas.ant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.bodz.bas.lang.err.IllegalUsageException;
import net.bodz.bas.util.Counter;
import net.bodz.bas.util.Counters;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class CountersTask extends Task implements IPureTask {

    private String       name;
    private Counters     counters;

    private File         loadFile;
    private String       classHint;

    private List<String> incrs;
    private boolean      update = true;

    public CountersTask() {
        incrs = new ArrayList<String>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFile(File file) throws IOException {
        if (classHint != null)
            throw new IllegalUsageException("already specified to load from " + classHint);
        this.loadFile = file;
    }

    public void setClassHint(String classHint) {
        if (loadFile != null)
            throw new IllegalUsageException("already specified to load from " + loadFile);
        this.classHint = classHint;
    }

    public void addConfiguredIncr(TextElement text) {
        incrs.add(text.getText());
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    @Override
    public void execute() throws BuildException {
        try {
            if (loadFile != null)
                counters = new Counters(loadFile);
            if (classHint != null) {
                Class<?> clazz = Class.forName(classHint);
                counters = new Counters(clazz);
            }
            for (Map.Entry<String, Counter> entry : counters.entrySet()) {
                String key = entry.getKey();
                Counter counter = entry.getValue();
                String property = name == null ? key : name + "." + key;
                String value = counter.format();
                getProject().setProperty(property, value);
            }
            for (String key : incrs) {
                Counter counter = counters.get(key);
                counter.increase();
            }
            if (update)
                counters.save();
        } catch (Exception e) {
            throw new BuildException(e);
        }
    }

}
