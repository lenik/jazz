package net.bodz.bas.html.artifact;

public class Group
        extends Artifact {

    private static final long serialVersionUID = 1L;

    public Group(String name) {
        super(name);
    }

    public Group(String name, String versionStr) {
        super(name, versionStr);
    }

    {
        setType(ArtifactType.GROUP);
    }

    @Override
    public Group dependsOn(IArtifact artifact) {
        super.dependsOn(artifact);
        return this;
    }

}
