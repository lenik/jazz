package net.bodz.bas.html.artifact;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.err.UnsupportedFeatureException;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.std.rfc.mime.ContentTypes;

public class ArtifactBuilder {

    private MutableArtifactManager container;

    public ArtifactBuilder() {
        this.container = new MutableArtifactManager();
    }

    public MutableArtifactManager getContainer() {
        return container;
    }

    public Artifact javascript(String name, String versionStr, IAnchor anchor) {
        Artifact artifact = new Artifact(name, versionStr);
        artifact.setContentType(ContentTypes.text_javascript);
        artifact.setAnchor(anchor);
        artifact.setType(ArtifactType.SCRIPT);
        afterCreation(artifact);
        return artifact;
    }

    public Artifact css(String name, String versionStr, IAnchor anchor) {
        Artifact artifact = new Artifact(name, versionStr);
        artifact.setContentType(ContentTypes.text_css);
        artifact.setAnchor(anchor);
        artifact.setType(ArtifactType.STYLESHEET);
        afterCreation(artifact);
        return artifact;
    }

    public Group group(String name) {
        Group group = new Group(name);
        afterCreation(group);
        return group;
    }

    public Group group(String name, IAnchor start, String... fragmentFiles) {
        Group group = group(name);

        IArtifact last = null;
        for (String file : fragmentFiles) {
            if (file.startsWith("#"))
                continue;
            IAnchor a = start.join(file);
            String version = "x";
            String base = FilePath.getBaseName(file);
            String ext = FilePath.getExtension(file, true);
            String fragmentName = name + "/" + base;
            IMutableArtifact fragment;
            if (".js".equals(ext))
                fragment = javascript(fragmentName, version, a);
            else if (".css".equals(ext))
                fragment = css(fragmentName, version, a);
            else
                throw new UnsupportedFeatureException("Extension: " + ext);
            if (last != null)
                fragment.addDependency(last);
            last = fragment;
        }
        group.addChild(last);
        return group;
    }

    protected void afterCreation(Artifact artifact) {
        if (container != null)
            container.addArtifact(artifact);
    }

}
