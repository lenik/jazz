package net.bodz.mda.loader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.cli.RunInfo;
import net.bodz.bas.cli.util.JavaLauncher;
import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.lang.util.Classpath;
import net.bodz.bas.sys.SystemInfo;
import net.bodz.bas.types.util.Empty;
import net.bodz.bas.types.util.RegexProcessor;
import net.bodz.bas.types.util.Strings;

@RunInfo(load = "findcp|$JAVA_HOME/lib/tools.jar")
public class JavaCompiler extends JavaLauncher {

    /** -source */
    private String     sourceVersion;

    /** -target */
    private String     targetVersion;

    /** -d, canonical file */
    private File       srcdir;

    /** -s, canonical file */
    private File       classdir;

    /** -encoding */
    private String     encoding = "utf-8";

    private List<File> classpaths;

    public JavaCompiler(File srcdir, File classdir) {
        this.srcdir = Files.canoniOf(srcdir);
        this.classdir = Files.canoniOf(classdir);
    }

    public JavaCompiler(File dir) {
        this(dir, null);
    }

    public JavaCompiler() {
        this(Files.getTmpDir());
    }

    @Override
    protected String getMainClassName() {
        return "com.sun.tools.javac.Main";
    }

    public File getSourceDir() {
        return srcdir;
    }

    public File getSourceFile(String name) {
        int d = name.indexOf('$');
        if (d != -1)
            name = name.substring(0, d);
        String fname = name.replace('.', '/') + ".java";
        return new File(srcdir, fname);
    }

    public File getClassDir() {
        return classdir != null ? classdir : srcdir;
    }

    public File getClassFile(String name) {
        String fname = name.replace('.', '/') + ".class";
        File dir = classdir;
        if (dir == null)
            dir = srcdir;
        return new File(dir, fname);
    }

    public void setClasspaths(List<File> classpaths) {
        this.classpaths = classpaths;
    }

    public void inheritsClasspath() {
        URL[] urls = Classpath.findURLs();
        for (URL url : urls) {
            try {
                File file = new File(url.toURI());
                addClasspath(file);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    public void addClasspath(File path) {
        if (classpaths == null)
            classpaths = new ArrayList<File>();
        classpaths.add(path);
    }

    public void compile(File file) throws IOException {
        List<String> list = new ArrayList<String>();
        if (classdir != null) {
            list.add("-d");
            list.add(classdir.getPath());
        }
        if (sourceVersion != null) {
            list.add("-source");
            list.add(sourceVersion);
        }
        if (targetVersion != null) {
            list.add("-target");
            list.add(targetVersion);
        }
        if (encoding != null) {
            list.add("-encoding");
            list.add(encoding);
        }
        // if (this.srcdir != null) {
        // list.add("-s");
        // list.add(this.srcdir.getPath());
        // }

        if (classpaths != null) {
            String cp = Strings.join(SystemInfo.pathSeparator, classpaths);
            list.add("-cp");
            list.add(cp);
        }

        // String relative = Files.getRelativeName(srcfile, srcdir);
        // list.add(relative);
        list.add(file.getPath());

        String[] args = list.toArray(Empty.Strings);

        try {
            launch(args);
        } catch (Exception e) {
            throw new CompilerError(e.getMessage(), e);
        }
    }

    public void compile(String java, String name) throws IOException {
        File srcfile = getSourceFile(name);
        srcfile.getParentFile().mkdirs();
        Files.write(srcfile, java, encoding);
        compile(srcfile);
    }

    public void compile(String java) throws IOException {
        String name = findTypeNames(java);
        compile(java, name);
    }

    @Override
    protected void _exit(int status) {
        if (status != 0) {
            String msg = "javac abnormally exited, exit status = " + status;
            throw new CompilerError(msg);
        }
    }

    private static Pattern javaNamesPattern;
    static {
        String qname = "(?:\\w(?:\\.\\w)*)";
        String java = "^(.*?package\\s+(" + qname + ")\\s*;)"
                + ".*?(interface|class|enum)\\s+(\\w+)\\b";
        javaNamesPattern = RegexProcessor.javaComments.compile(java,
                Pattern.DOTALL);
    }

    public static String findTypeNames(String java) {
        if (true)
            throw new NotImplementedException();
        Matcher m = javaNamesPattern.matcher(java);
        String packageName = null;
        String typeName = null;
        if (m.find()) {
            if (m.group(1) != null)
                packageName = m.group(2);
            typeName = m.group(3);
            if (packageName != null)
                typeName = packageName + "." + typeName;
            return typeName;
        }
        return null;
    }

}
