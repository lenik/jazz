package net.bodz.mda.xjdoc.model1;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class MutableArtifactObject
        extends MutableArtifactElement {

    private static final long serialVersionUID = 1L;

    transient boolean artifactDocLoaded;

    /**
     * @return Non-<code>null</code> {@link ArtifactDoc}.
     */
    @Override
    public ArtifactDoc getArtifactDoc() {
        if (artifactDoc == null) {
            synchronized (this) {
                if (!artifactDocLoaded) {
                    artifactDoc = loadArtifactDoc();
                    artifactDocLoaded = true;
                }
            }
            if (artifactDoc == null)
                throw new IllegalUsageException("Artifact doc isn't set.");
        }
        return artifactDoc;
    }

    protected ArtifactDoc loadArtifactDoc() {
        ClassDoc classDoc = ClassDocs.loadFromResource(getClass(), true);
        ArtifactDoc artifactDoc = classDoc.as(ArtifactDoc.class);
        return artifactDoc;
    }

}
