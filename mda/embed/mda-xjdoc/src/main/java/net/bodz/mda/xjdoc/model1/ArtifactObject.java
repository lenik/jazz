package net.bodz.mda.xjdoc.model1;

import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class ArtifactObject
        extends AbstractArtifactElement {

    @Override
    public ArtifactDoc loadArtifactDoc() {
        ClassDoc classDoc = ClassDocs.loadFromResource(getClass(), true);
        ArtifactDoc artifactDoc = classDoc.as(ArtifactDoc.class);
        return artifactDoc;
    }

}
