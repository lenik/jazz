package net.bodz.mda.xjdoc.model1;

import java.io.Serializable;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom1.MutableElement;

public class MutableArtifactElement
        extends MutableElement
        implements IArtifactElement, Serializable {

    private static final long serialVersionUID = 1L;

    ArtifactDoc artifactDoc;

    /**
     * @return Non-<code>null</code> {@link ArtifactDoc}.
     */
    @Override
    public ArtifactDoc getArtifactDoc() {
        return artifactDoc;
    }

    public void setArtifactDoc(ArtifactDoc artifactDoc, boolean applyToElementProperties) {
        this.artifactDoc = artifactDoc;

        if (applyToElementProperties) {
            if (artifactDoc == null)
                throw new NullPointerException("artifactDoc");

            setName(artifactDoc.getName());
            setDisplayName(artifactDoc.getDisplayName());

            DomainString text = artifactDoc.getText();
            if (text != null) {
                setDescription(text.headPar());
                setHelpDoc(text.tailPar());
            }
        }
    }

}
