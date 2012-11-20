package net.bodz.mda.xjdoc.model1;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.DomainStrings;
import net.bodz.bas.i18n.dom1.AbstractElement;
import net.bodz.mda.xjdoc.conv.ClassDocs;

public abstract class ArtifactElement
        extends AbstractElement {

    transient ArtifactDoc artifactDoc;

    transient DomainString displayName;
    transient DomainString description;
    transient DomainString helpDoc;

    public ArtifactElement() {
        artifactDoc = ClassDocs.loadFromResource(getClass()).as(ArtifactDoc.class);

        DomainString header = DomainStrings.getTextHeader(artifactDoc.getText());
        DomainString body = DomainStrings.getTextBody(artifactDoc.getText());
        description = header;
        helpDoc = body;
    }

    @Override
    public DomainString getDisplayName() {
        return artifactDoc.getLabel();
    }

    @Override
    public DomainString getDescription() {
        return description;
    }

    @Override
    public DomainString getHelpDoc() {
        return helpDoc;
    }

}
