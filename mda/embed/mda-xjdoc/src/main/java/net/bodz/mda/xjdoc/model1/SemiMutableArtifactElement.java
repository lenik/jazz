package net.bodz.mda.xjdoc.model1;

import java.io.Serializable;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.i18n.dom1.SemiMutableElement;

public abstract class SemiMutableArtifactElement
        extends SemiMutableElement
        implements IArtifactElement, Serializable {

    private static final long serialVersionUID = 1L;

    transient ArtifactDoc artifactDoc;
    transient boolean artifactDocLoaded;

    /**
     * @return Non-<code>null</code> {@link ArtifactDoc}.
     */
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
        return null;
    }

}
