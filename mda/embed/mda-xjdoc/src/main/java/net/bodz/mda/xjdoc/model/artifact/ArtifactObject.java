package net.bodz.mda.xjdoc.model.artifact;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.mda.xjdoc.XjdocLoaderException;
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
    protected ArtifactDoc loadXjdoc()
            throws XjdocLoaderException, ParseException, IOException {
        IElementDoc xjdoc = super.loadXjdoc();
        // xjdoc.as(ArtifactDoc.class);
        ArtifactDoc artifactDoc = new ArtifactDoc(xjdoc);
        return artifactDoc;
    }

}
