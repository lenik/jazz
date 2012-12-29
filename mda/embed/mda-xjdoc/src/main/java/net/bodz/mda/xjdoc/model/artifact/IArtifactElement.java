package net.bodz.mda.xjdoc.model.artifact;

import net.bodz.mda.xjdoc.model.javadoc.IXjdocElement;

public interface IArtifactElement
        extends IXjdocElement {

    @Override
    ArtifactDoc getXjdoc();

}
