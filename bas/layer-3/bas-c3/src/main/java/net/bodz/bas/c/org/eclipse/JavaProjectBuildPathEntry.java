package net.bodz.bas.c.org.eclipse;

import java.io.File;
import java.net.URL;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.FileURL;

public class JavaProjectBuildPathEntry {

    URL sourceURL;
    URL targetURL;

    public JavaProjectBuildPathEntry(JavaProjectSourceFolder sourceFolder, File defaultOutput) {
        this.sourceURL = FileURL.toURL(sourceFolder.path);
        if (sourceFolder.output == null)
            this.targetURL = FileURL.toURL(defaultOutput);
        else
            this.targetURL = FileURL.toURL(sourceFolder.output);
    }

    public JavaProjectBuildPathEntry(JavaProjectLibraryRef library) {
        this(library.sourceAttachment, library.path);
    }

    public JavaProjectBuildPathEntry(File sourceLoc, File targetLoc, String... searchSourceDirSuffixes) {
        if (targetLoc == null)
            throw new NullPointerException("targetLoc");
        this.targetURL = FileURL.toURL(targetLoc);

        if (sourceLoc == null) {
            File parentDir = targetLoc.getParentFile();
            String base = targetLoc.getName();
            String baseName = FilePath.stripExtension(base);
            String baseExt = FilePath.getExtension(base, true);
            for (String suffix : searchSourceDirSuffixes) {
                File f = new File(parentDir, baseName + suffix + baseExt);
                if (f.exists()) {
                    sourceLoc = f;
                    break;
                }
            }
        }
        this.sourceURL = sourceLoc == null ? null : FileURL.toURL(sourceLoc);
    }

    public URL getSourceURL() {
        return sourceURL;
    }

    public URL getTargetURL() {
        return targetURL;
    }

    public File getSourcePath() {
        if (sourceURL == null)
            return null;
        else
            return FileURL.toFile(sourceURL, null);
    }

    public File getTargetPath() {
        if (targetURL == null)
            return null;
        else
            return FileURL.toFile(targetURL, null);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        if (targetURL != null)
            buf.append("target: " + targetURL);
        buf.append(", ");
        if (sourceURL != null)
            buf.append("source: " + sourceURL);
        if (buf.length() == 0)
            return "(n/a)";
        return buf.toString();
    }

}
