package net.bodz.bas.ant;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Path;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.cli.boot.win32.Mkbat;

public class MkbatTask
        extends CLITask {

    private final Mkbat mkbat;

    // private File srcdir;
    private List<Path> classpathList;
    private List<Path> findmainList;
    private List<String> runtimeLibs;

    public MkbatTask() {
        super(new Mkbat());
        mkbat = (Mkbat) super.program;
        addArguments("-rq", "--");
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
        return (File) get("outputDirectory");
    }

    public void setOutdir(File outdir) {
        set("outputDirectory", outdir);
    }

    static String pathSeparator;
    static {
        pathSeparator = ";";
    }

    public void addClasspath(Path path) {
        classpathList.add(path);
    }

    @Override
    public void execute()
            throws BuildException {
        for (Path classpath : classpathList) {
            String[] paths = classpath.list();
            for (int i = 0; i < paths.length; i++) {
                File file = new File(paths[i]);
                URL url = FileURL.toURL(file, null);
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
