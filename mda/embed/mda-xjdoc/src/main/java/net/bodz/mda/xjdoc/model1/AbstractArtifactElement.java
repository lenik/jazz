package net.bodz.mda.xjdoc.model1;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom1.AbstractElement;

/**
 * An artifact is a "product"-like component, with well created documents.
 * 
 * The default implementation get the documents from some "reflective" sources.
 */
public abstract class AbstractArtifactElement
        extends AbstractElement
        implements IArtifactElement {

    transient ArtifactDoc artifactDoc;
    transient boolean artifactDocLoaded;

    transient DomainString displayName;
    transient DomainString description;
    transient DomainString helpDoc;

    public AbstractArtifactElement() {
    }

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

    @Override
    public String getName() {
        return getArtifactDoc().getName();
    }

    @Override
    public DomainString getDisplayName() {
        if (displayName == null)
            displayName = getArtifactDoc().getDisplayName();
        return displayName;
    }

    /**
     * The default description is the header line of the text.
     */
    @Override
    public synchronized DomainString getDescription() {
        if (description == null) {
            DomainString text = getArtifactDoc().getText();
            if (text != null)
                description = text.headPar();
        }
        return description;
    }

    /**
     * The default helpDoc is from the text without the header line.
     */
    @Override
    public synchronized DomainString getHelpDoc() {
        if (helpDoc == null) {
            DomainString text = getArtifactDoc().getText();
            if (text != null)
                helpDoc = text.tailPar();
        }
        return helpDoc;
    }

    protected void setArtifactDoc(ArtifactDoc artifactDoc) {
        this.artifactDoc = artifactDoc;
        this.artifactDocLoaded = true;
    }

    protected void setDisplayName(DomainString displayName) {
        this.displayName = displayName;
    }

    protected void setDescription(DomainString description) {
        this.description = description;
    }

    protected void setHelpDoc(DomainString helpDoc) {
        this.helpDoc = helpDoc;
    }

}
