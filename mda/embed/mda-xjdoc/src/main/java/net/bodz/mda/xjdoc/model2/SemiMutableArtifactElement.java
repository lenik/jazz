package net.bodz.mda.xjdoc.model2;

import net.bodz.mda.xjdoc.model.IJavaElementDoc;
import net.bodz.mda.xjdoc.model1.SemiMutableXjdocElement;

public class SemiMutableArtifactElement
        extends SemiMutableXjdocElement {

    private static final long serialVersionUID = 1L;

    @Override
    public ArtifactDoc getXjdoc() {
        IJavaElementDoc xjdoc = super.getXjdoc();
        ArtifactDoc artifactDoc = new ArtifactDoc(xjdoc);
        return artifactDoc;
    }

    @Override
    protected ArtifactDoc loadXjdoc() {
        return null;
    }

}
