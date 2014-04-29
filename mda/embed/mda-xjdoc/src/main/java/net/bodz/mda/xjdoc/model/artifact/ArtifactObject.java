package net.bodz.mda.xjdoc.model.artifact;

import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.XjdocObject;

public abstract class ArtifactObject
        extends XjdocObject {

    // Java Reflection: A method 'public IJavaElementDoc getXjdoc()' is auto created.
    // With the modifier SYNTHETIC | VOLATILE set.

    @Override
    public ArtifactDoc getXjdoc() {
        return (ArtifactDoc) super.getXjdoc();
    }

    @Override
    public ArtifactDoc loadXjdoc() {
        IElementDoc xjdoc = super.loadXjdoc();
        // xjdoc.as(ArtifactDoc.class);
        ArtifactDoc artifactDoc = new ArtifactDoc(xjdoc);
        return artifactDoc;
    }

}
