package net.bodz.mda.xjdoc.model.artifact;

import net.bodz.mda.xjdoc.model.IJavaElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.MutableXjdocElement;

public class MutableArtifactElement
        extends MutableXjdocElement {

    private static final long serialVersionUID = 1L;

    @Override
    public ArtifactDoc getXjdoc() {
        return (ArtifactDoc) super.getXjdoc();
    }

    @Override
    public final void setXjdoc(IJavaElementDoc xjdoc) {
        setXjdoc(xjdoc, false);
    }

    @Override
    public final void setXjdoc(IJavaElementDoc xjdoc, boolean applyToElementProperties) {
        ArtifactDoc artifactDoc = (ArtifactDoc) xjdoc;
        setArtifactDoc(artifactDoc, applyToElementProperties);
    }

    public void setArtifactDoc(ArtifactDoc artifactDoc, boolean applyToElementProperties) {
        super.setXjdoc(artifactDoc, applyToElementProperties);
    }

}
