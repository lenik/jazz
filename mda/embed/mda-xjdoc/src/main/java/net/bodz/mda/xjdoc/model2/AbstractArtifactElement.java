package net.bodz.mda.xjdoc.model2;

import net.bodz.mda.xjdoc.model1.AbstractXjdocElement;

/**
 * An artifact is a "product"-like component, with well created documents.
 * 
 * The default implementation get the documents from some "reflective" sources.
 */
public abstract class AbstractArtifactElement
        extends AbstractXjdocElement {

    @Override
    public ArtifactDoc getXjdoc() {
        return (ArtifactDoc) super.getXjdoc();
    }

    @Override
    protected ArtifactDoc loadXjdoc() {
        return null;
    }

}
