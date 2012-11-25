package net.bodz.mda.xjdoc.model1;

import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class ArtifactObject
        extends AbstractArtifactElement {

    @Override
    public synchronized ArtifactDoc getArtifactDoc() {
        if (_artifactDoc == null) {
            ClassDoc classDoc = ClassDocs.loadFromResource(getClass(), true);
            _artifactDoc = classDoc.as(ArtifactDoc.class);
        }
        return _artifactDoc;
    }

}
