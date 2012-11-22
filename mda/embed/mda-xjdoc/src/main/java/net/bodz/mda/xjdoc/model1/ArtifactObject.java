package net.bodz.mda.xjdoc.model1;

import net.bodz.mda.xjdoc.conv.ClassDocs;

public class ArtifactObject
        extends AbstractArtifactElement {

    @Override
    public synchronized ArtifactDoc getArtifactDoc() {
        if (_artifactDoc == null)
            _artifactDoc = ClassDocs.loadFromResource(getClass()).as(ArtifactDoc.class);
        return _artifactDoc;
    }

}
