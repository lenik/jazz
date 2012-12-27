package net.bodz.mda.xjdoc.model2;

import net.bodz.mda.xjdoc.model.IJavaElementDoc;
import net.bodz.mda.xjdoc.model1.XjdocObject;

public abstract class ArtifactObject
        extends XjdocObject {

    @Override
    public ArtifactDoc getXjdoc() {
        return (ArtifactDoc) super.getXjdoc();
    }

    @Override
    public ArtifactDoc loadXjdoc() {
        IJavaElementDoc xjdoc = super.loadXjdoc();
        // xjdoc.as(ArtifactDoc.class);
        ArtifactDoc artifactDoc = new ArtifactDoc(xjdoc);
        return artifactDoc;
    }

}
