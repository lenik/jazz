package net.bodz.bas.snm.eclipse;

import java.io.File;
import java.net.URL;
import java.util.List;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.snm.eclipse.BuildPath.ClassContainer;
import net.bodz.bas.snm.eclipse.BuildPath.SourceFolder;

public class EclipseProject {

    File baseDir;
    BuildPath buildPath;

    public EclipseProject(File baseDir)
            throws ParseException {
        assert baseDir != null : "null base";
        this.baseDir = baseDir;
        File classpathFile = new File(baseDir, ".classpath");
        File manifestFile = new File(baseDir, "META-INF/MANIFEST.MF");
        buildPath = new BuildPath(baseDir, classpathFile, manifestFile);
    }

    public static EclipseProject findFromCWD()
            throws ParseException {
        File base = findProjectBase(new File("."));
        if (base == null)
            throw new RuntimeException("can\'t find the project");
        return new EclipseProject(base);
    }

    public static File findProjectBase(File dir) {
        if (dir != null && !dir.isDirectory())
            return null;
        while (dir != null) {
            if (new File(dir, ".project").exists())
                return dir;
            dir = dir.getParentFile();
        }
        return null;
    }

    public static File findProjectBase(Class<?> clazz) {
        File base = JarLocations.getBaseClasspath(clazz);
        return findProjectBase(base);
    }

    public File getBase() {
        return baseDir;
    }

    public BuildPath getBuildPath() {
        return buildPath;
    }

    public List<File> getFileClasspath()
            throws ParseException {
        return buildPath.getClasspath();
    }

    /**
     * Convert classpaths to URL[].
     */
    public URL[] getURLClasspath()
            throws ParseException {
        List<File> classpaths = getFileClasspath();
        URL[] urls = new URL[classpaths.size()];
        int i = 0;
        for (File classpath : classpaths) {
            URL url = FileURL.toURL(classpath, null);
            urls[i++] = url;
        }
        return urls;
    }

    public EclipseWorkspace findWorkspace() {
        throw new NotImplementedException();
    }

    public static SourceFolder getSourceFolder(Class<?> clazz) {
        File projectBase = findProjectBase(clazz);
        BuildPath buildPath;
        try {
            buildPath = new EclipseProject(projectBase).getBuildPath();
        } catch (ParseException e) {
            throw new IllegalUsageException("Class isn't belong to project: " + clazz);
        }
        ClassContainer cc = buildPath.whichClassContainer(clazz);
        if (cc == null)
            throw new UnexpectedException("No matching class container for: " + clazz);
        File sourcePath = cc.getSourcePath();
        if (sourcePath == null)
            throw new UnexpectedException("Class isn't belong to any source folder: " + clazz);
        SourceFolder sourceFolder = buildPath.whichSourceFolder(sourcePath);
        assert sourceFolder != null;
        return sourceFolder;
    }

}
