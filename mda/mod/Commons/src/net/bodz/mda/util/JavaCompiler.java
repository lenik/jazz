package net.bodz.mda.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.lang.util.Reflects;
import net.bodz.bas.types.util.Empty;
import net.bodz.bas.types.util.RegexProcessor;

public class JavaCompiler {

    static String  javacMainClassName = "com.sun.tools.javac.Main";
    static Method  javacMainCompile;

    /** -source */
    private String sourceVersion;
    /** -target */
    private String targetVersion;
    /** -d */
    private File   srcdir;
    /** -s */
    private File   classdir;
    /** -encoding */
    private String encoding           = "utf-8";

    public JavaCompiler(File srcdir, File classdir) {
        this.srcdir = srcdir;
        this.classdir = classdir;
    }

    public JavaCompiler(File dir) {
        this(dir, null);
    }

    public JavaCompiler() {
        this(Files.getTmpDir());
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

    void compile(File file) throws IOException {
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
        // String relative = Files.getRelativeName(srcfile, srcdir);
        // list.add(relative);
        list.add(file.getPath());

        String[] args = list.toArray(Empty.Strings);
        StringWriter outbuf = new StringWriter();
        PrintWriter out = new PrintWriter(outbuf, true);
        int exit = (Integer) Reflects.invoke(null, //
                javacMainCompile, args, out);
        if (exit != 0)
            throw new CompilerError(outbuf.toString());
    }

    void compile(String java, String name) throws IOException {
        if (javacMainCompile == null) {
            try {
                Class<?> c = Class.forName(javacMainClassName);
                javacMainCompile = c.getMethod("compile", String[].class,
                        PrintWriter.class);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            } catch (NoSuchMethodException e) {
                throw new NoSuchMethodError(e.getMessage());
            }
        }
        File srcfile = getSourceFile(name);
        srcfile.getParentFile().mkdirs();
        Files.write(srcfile, java, encoding);
        compile(srcfile);
    }

    public void compile(String java) throws IOException {
        String name = findTypeNames(java);
        compile(java, name);
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
