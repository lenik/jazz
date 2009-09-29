package net.bodz.bas.ant;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.cli.util.CLITask;
import net.bodz.bas.cli.util.Mkbat;
import net.bodz.bas.io.Files;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Path;

public class MkbatTask extends CLITask {

    private final Mkbat  mkbat;

    // private File srcdir;
    private List<Path>   classpathList;
    private List<Path>   findmainList;
    private List<String> runtimeLibs;

    public MkbatTask() {
        super(new Mkbat());
        mkbat = (Mkbat) super.app;
        addArguments("-rq", "--"); //$NON-NLS-1$ //$NON-NLS-2$
        classpathList = new ArrayList<Path>();
        findmainList = new ArrayList<Path>();
        runtimeLibs = new ArrayList<String>();
    }

    public void addFindMain(Path classpath) {
        findmainList.add(classpath);
    }

    public void addConfiguredLibrary(Parameter parameter) {
        String library = parameter.getText().trim();
        runtimeLibs.add(library);
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

    public void addClasspath(Path path) {
        classpathList.add(path);
    }

    @Override
    public void execute() throws BuildException {
        for (Path classpath : classpathList) {
            String[] paths = classpath.list();
            for (int i = 0; i < paths.length; i++) {
                File file = new File(paths[i]);
                URL url = Files.getURL(file);
                mkbat.addClasspath(url);
            }
        }

        for (String runtimeLib : runtimeLibs)
            mkbat.addRuntimeLib(runtimeLib);

        for (Path srcdir : findmainList) {
            for (String path : srcdir.list()) {
                // log("-> " + path);
                addArguments(path);
            }
        }
        super.execute();
    }

}
