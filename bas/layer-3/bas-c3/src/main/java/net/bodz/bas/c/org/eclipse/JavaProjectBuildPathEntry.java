package net.bodz.bas.c.org.eclipse;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.FileURL;

public class JavaProjectBuildPathEntry {

    URL sourceURL;
    URL targetURL;

    public JavaProjectBuildPathEntry(JavaProjectSourceFolder sourceFolder, Path defaultOutput) {
        this.sourceURL = FileURL.toURL(sourceFolder.path);
        if (sourceFolder.output == null)
            this.targetURL = FileURL.toURL(defaultOutput);
        else
            this.targetURL = FileURL.toURL(sourceFolder.output);
    }

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

    public JavaProjectBuildPathEntry(Path sourceLoc, Path targetLoc, String... searchSourceDirSuffixes) {
        if (targetLoc == null)
            throw new NullPointerException("targetLoc");
        this.targetURL = FileURL.toURL(targetLoc);

        if (sourceLoc == null) {
            Path parentDir = targetLoc.getParent();
            String base = targetLoc.getFileName().toString();
            String baseName = FilePath.name(base);
            String baseExt = FilePath.dotExtension(base);
            for (String suffix : searchSourceDirSuffixes) {
                Path f = parentDir.resolve(baseName + suffix + baseExt);
                if (Files.exists(f)) {
                    sourceLoc = f;
                    break;
                }
            }
        }
        this.sourceURL = sourceLoc == null ? null : FileURL.toURL(sourceLoc);
    }

    public JavaProjectBuildPathEntry(File sourceLoc, File targetLoc, String... searchSourceDirSuffixes) {
        if (targetLoc == null)
            throw new NullPointerException("targetLoc");
        this.targetURL = FileURL.toURL(targetLoc);

        if (sourceLoc == null) {
            File parentDir = targetLoc.getParentFile();
            String base = targetLoc.getName();
            String baseName = FilePath.name(base);
            String baseExt = FilePath.dotExtension(base);
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

    public Path getSourcePath() {
        if (sourceURL == null)
            return null;
        else
            return FileURL.toPath(sourceURL, null);
    }

    public Path getTargetPath() {
        if (targetURL == null)
            return null;
        else
            return FileURL.toPath(targetURL, null);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        if (targetURL != null)
            buf.append("target: ").append(targetURL);
        buf.append(", ");
        if (sourceURL != null)
            buf.append("source: ").append(sourceURL);
        if (buf.length() == 0)
            return "(n/a)";
        return buf.toString();
    }

}
