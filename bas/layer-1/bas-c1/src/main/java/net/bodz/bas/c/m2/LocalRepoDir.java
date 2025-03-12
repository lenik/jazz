package net.bodz.bas.c.m2;

import java.nio.file.Files;
import java.nio.file.Path;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.FileRelation;
import net.bodz.bas.c.system.SysProps;

public class LocalRepoDir {

    Path startDir;

    //    public LocalRepoDir(File startDir) {
    public LocalRepoDir(Path startDir) {
        if (startDir == null)
            throw new NullPointerException("startDir");
        this.startDir = startDir;
    }

    /**
     * @return <code>null</code> if not found.
     */
    public static LocalRepoDir closest(Path dir) {
        if (dir == null)
            return null;
        if (Files.isRegularFile(dir))
            return closest(dir.getParent());

        Path repoXml = dir.resolve("repository.xml");
        if (Files.exists(repoXml)) {
            Path fullPath = dir.toAbsolutePath();
            return new LocalRepoDir(fullPath);
        }
        return closest(dir.getParent());
    }

    public ArtifactId getQualifiedName(Path jar) {
        Path common = FileRelation.getCommonParentFile(startDir, jar);
        if (common == null)
            return null;

        assert common.equals(startDir);
        if (!common.equals(startDir))
            return null;

        Path versionDir = jar.getParent();
        Path artifactDir = versionDir.getParent();
        Path groupDir = artifactDir.getParent();

        String startPath = startDir.toString();
        String groupPath = groupDir.toString();
        groupPath = groupPath.substring(startPath.length() + 1);

        ArtifactId id = new ArtifactId();
        id.groupId = groupPath.replace(SysProps.fileSep, ".");
        id.artifactId = artifactDir.getFileName().toString();
        id.version = versionDir.getFileName().toString();

        String name = jar.getFileName().toString();
        id.packaging = FilePath.getExtension(name, false);

        name = FilePath.stripExtension(name);
        String suffix = name.substring(id.artifactId.length() + 1 + id.version.length());
        if (!suffix.isEmpty())
            id.scope = suffix.substring(1);
        return id;
    }

}
