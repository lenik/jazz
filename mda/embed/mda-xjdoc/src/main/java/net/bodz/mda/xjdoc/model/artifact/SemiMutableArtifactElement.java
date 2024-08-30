package net.bodz.mda.xjdoc.model.artifact;

import net.bodz.mda.xjdoc.model.IMutableElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.SemiMutableXjdocElement;

public class SemiMutableArtifactElement
        extends SemiMutableXjdocElement {

    private static final long serialVersionUID = 1L;

    @Override
    public ArtifactDoc getXjdoc() {
        IMutableElementDoc xjdoc = (IMutableElementDoc) super.getXjdoc();
        ArtifactDoc artifactDoc = new ArtifactDoc(xjdoc);
        return artifactDoc;
    }

    @Override
    protected ArtifactDoc loadXjdoc() {
        return null;
    }

}
