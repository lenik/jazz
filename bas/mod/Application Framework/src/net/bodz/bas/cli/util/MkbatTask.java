package net.bodz.bas.cli.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.io.Files;

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
        addArguments("-rq", "--"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public void addSrcdir(Path srcdir) {
        srcdirList.add(srcdir);
    }

    public File getOutdir() {
        return (File) get("outputDirectory"); //$NON-NLS-1$
    }

    public void setOutdir(File outdir) {
        set("outputDirectory", outdir); //$NON-NLS-1$
    }

    static String pathSeparator;
    static {
        pathSeparator = ";"; //$NON-NLS-1$
    }

    public void addUserlibs(Path userlibs) {
        userlibsList.add(userlibs);
    }

    @Override
    public void execute() throws BuildException {
        for (Path userlibs : userlibsList) {
            String[] paths = userlibs.list();
            for (int i = 0; i < paths.length; i++) {
                File file = new File(paths[i]);
                URL url = Files.getURL(file);
                mkbat.addUserLib(url);
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
