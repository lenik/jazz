package net.bodz.bas.cli.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Path;

public class MkbatTask extends CLITask {

    private final Mkbat mkbat;

    private File        srcdir;
    private Path        userlibs;

    public MkbatTask() {
        super(new Mkbat());
        mkbat = (Mkbat) super.app;
        addArguments("-rq", "--");
    }

    public File getSrcdir() {
        return srcdir;
    }

    public void setSrcdir(File srcdir) {
        this.srcdir = srcdir;
    }

    public File getOutdir() {
        return (File) get("outputDirectory");
    }

    public void setOutdir(File outdir) {
        set("outputDirectory", outdir);
    }

    static String pathSeparator;
    static {
        pathSeparator = ";";
    }

    public Path createUserlibs() {
        return userlibs = new Path(getProject());
    }

    public void addUserlibs(Path userlibs) { // XXX
        this.userlibs = userlibs;
    }

    @Override
    public void execute() throws BuildException {
        if (userlibs != null) {
            String[] paths = userlibs.list();
            for (int i = 0; i < paths.length; i++) {
                String path = paths[i];
                try {
                    URL url = new File(path).toURI().toURL();
                    mkbat.addUserLib(url);
                } catch (MalformedURLException e) {
                    throw new BuildException(e);
                }
            }
        }
        if (srcdir != null)
            addArguments(srcdir.getPath());
        super.execute();
    }
}
