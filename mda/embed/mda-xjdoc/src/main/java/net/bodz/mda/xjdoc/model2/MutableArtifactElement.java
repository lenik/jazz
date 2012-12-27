package net.bodz.mda.xjdoc.model2;

import net.bodz.mda.xjdoc.model.IJavaElementDoc;
import net.bodz.mda.xjdoc.model1.MutableXjdocElement;

public class MutableArtifactElement
        extends MutableXjdocElement {

    private static final long serialVersionUID = 1L;

    @Override
    public ArtifactDoc getXjdoc() {
        return (ArtifactDoc) super.getXjdoc();
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
