package net.bodz.bas.c.m2;

public class ArtifactId {

    public String groupId;
    public String artifactId;
    public String packaging;
    public String version;
    public String scope;

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
        StringBuilder sb = new StringBuilder(80);
        sb.append(groupId);
        sb.append(":");
        sb.append(artifactId);
        if (version != null) {
            sb.append(":");
            sb.append(version);
        }
        if (scope != null) {
            sb.append(":");
            sb.append(scope);
        }
        return sb.toString();
    }

}
