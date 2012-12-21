package net.bodz.bas.c.org.eclipse;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;

public class JavaProjectSourceFolder {

    public File path;
    public File output;
    public String[] includes;
    public String[] excludes;
    public File nativeLibraryLocation;

    @Override
    public String toString() {
        return String.valueOf(path);
    }

    public static JavaProjectSourceFolder forClass(Class<?> clazz) {
        File baseDir = JavaProjectBaseDir.forClass(clazz);

        JavaProject project;
        try {
            project = new JavaProject(baseDir);
        } catch (ParseException e) {
            throw new IllegalUsageException("Class isn't belong to the project: " + clazz);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        JavaProjectBuildPathEntry buildPathEntry = project.findBuildPathEntry(clazz);
        if (buildPathEntry == null)
            return null; // No build path entry.

        File sourcePath = buildPathEntry.getSourcePath();
        if (sourcePath == null)
            return null; // No source folder.

        return project.findSourceFolder(sourcePath);
    }

}