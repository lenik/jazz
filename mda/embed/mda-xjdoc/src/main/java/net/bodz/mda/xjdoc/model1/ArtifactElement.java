package net.bodz.mda.xjdoc.model1;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom1.AbstractElement;
import net.bodz.mda.xjdoc.conv.ClassDocs;

public abstract class ArtifactElement
        extends AbstractElement {

    transient ArtifactDoc _artifactDoc;

    transient DomainString displayName;
    transient DomainString description;
    transient DomainString helpDoc;

    public ArtifactElement() {
    }

    public synchronized ArtifactDoc getArtifactDoc() {
        if (_artifactDoc == null)
            _artifactDoc = ClassDocs.loadFromResource(getClass()).as(ArtifactDoc.class);
        return _artifactDoc;
    }

    @Override
    public DomainString getDisplayName() {
        return getArtifactDoc().getLabel();
    }

    @Override
    public synchronized DomainString getDescription() {
        if (description == null) {
            DomainString text = getArtifactDoc().getText();
            description = text.headPar();
        }
        return description;
    }

    @Override
    public synchronized DomainString getHelpDoc() {
        if (helpDoc == null) {
            DomainString text = getArtifactDoc().getText();
            helpDoc = text.tailPar();
        }
        return helpDoc;
    }

}
