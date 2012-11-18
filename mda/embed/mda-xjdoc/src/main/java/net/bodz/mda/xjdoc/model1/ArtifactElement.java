package net.bodz.mda.xjdoc.model1;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.DomainStrings;
import net.bodz.bas.i18n.dom1.AbstractElement;
import net.bodz.mda.xjdoc.conv.ClassDocs;

public abstract class ArtifactElement
        extends AbstractElement {

    ArtifactDoc artifactDoc;

    public ArtifactElement() {
        artifactDoc = ClassDocs.loadFromResource(getClass()).as(ArtifactDoc.class);

        // String description = artifactDoc.getTextHeader();
        DomainString header = DomainStrings.getTextHeader(artifactDoc.getText());
        DomainString body = DomainStrings.getTextBody(artifactDoc.getText());
        setDescription(header);
        setHelpDoc(body);
    }

}
