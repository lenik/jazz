package net.bodz.mda.xjdoc.model1;

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

    transient ArtifactDoc _artifactDoc;

    transient DomainString displayName;
    transient DomainString description;
    transient DomainString helpDoc;

    public AbstractArtifactElement() {
    }

    /**
     * @return Non-<code>null</code> {@link ArtifactDoc}.
     */
    public abstract ArtifactDoc getArtifactDoc();

    @Override
    public String getName() {
        return getArtifactDoc().getName();
    }

    @Override
    public DomainString getDisplayName() {
        return getArtifactDoc().getLabel();
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

}
