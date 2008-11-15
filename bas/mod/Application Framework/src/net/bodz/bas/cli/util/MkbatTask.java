package net.bodz.bas.cli.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Path;

public class MkbatTask extends CLITask {

    private final Mkbat mkbat;

    // private File srcdir;
    private List<Path>  srcdirList   = new ArrayList<Path>();
    private List<Path>  userlibsList = new ArrayList<Path>();

    public MkbatTask() {
        super(new Mkbat());
        mkbat = (Mkbat) super.app;
        addArguments("-rq", "--");
    }

    public void addSrcdir(Path srcdir) {
        srcdirList.add(srcdir);
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

    public void addUserlibs(Path userlibs) {
        userlibsList.add(userlibs);
    }

    @Override
    public void execute() throws BuildException {
        for (Path userlibs : userlibsList) {
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
        for (Path srcdir : srcdirList) {
            for (String path : srcdir.list()) {
                // log("-> " + path);
                addArguments(path);
            }
        }
        super.execute();
    }
}
