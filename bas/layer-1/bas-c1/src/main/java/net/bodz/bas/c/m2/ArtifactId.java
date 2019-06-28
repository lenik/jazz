package net.bodz.bas.c.m2;

public class ArtifactId {

    public String groupId;
    public String artifactId;
    public String packaging;
    public String version;

    public ArtifactId() {
    }

    public ArtifactId(String groupId, String artifactId, String packaging, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.packaging = packaging;
        this.version = version;
    }

    @Override
    public String toString() {
        return groupId + ":" + artifactId + ":" + version;
    }

}
