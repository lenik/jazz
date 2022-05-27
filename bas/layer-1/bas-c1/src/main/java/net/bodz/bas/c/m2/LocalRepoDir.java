package net.bodz.bas.c.m2;

import java.io.File;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.FileRelation;
import net.bodz.bas.c.system.SysProps;

public class LocalRepoDir {

    File startDir;

    public LocalRepoDir(File startDir) {
        if (startDir == null)
            throw new NullPointerException("startDir");
        this.startDir = startDir;
    }

    /**
     * @return <code>null</code> if not found.
     */
    public static LocalRepoDir closest(File dir) {
        if (dir == null)
            return null;
        if (dir.isFile())
            return closest(dir.getParentFile());

        File repoXml = new File(dir, "repository.xml");
        if (repoXml.exists()) {
            File fullPath = dir.getAbsoluteFile();
            return new LocalRepoDir(fullPath);
        }
        return closest(dir.getParentFile());
    }

    public ArtifactId getQualifiedName(File jar) {
        File common = FileRelation.getCommonParentFile(startDir, jar);
        if (common == null)
            return null;

        assert common.equals(startDir);
        if (!common.equals(startDir))
            return null;

        File versionDir = jar.getParentFile();
        File artifactDir = versionDir.getParentFile();
        File groupDir = artifactDir.getParentFile();

        String startPath = startDir.getPath();
        String groupPath = groupDir.getPath();
        groupPath = groupPath.substring(startPath.length() + 1);

        ArtifactId id = new ArtifactId();
        id.groupId = groupPath.replace(SysProps.fileSep, ".");
        id.artifactId = artifactDir.getName();
        id.version = versionDir.getName();

        String name = jar.getName();
        id.packaging = FilePath.getExtension(name, false);

        name = FilePath.stripExtension(name);
        String suffix = name.substring(id.artifactId.length() + 1 + id.version.length());
        if (!suffix.isEmpty())
            id.scope = suffix.substring(1);
        return id;
    }

}
