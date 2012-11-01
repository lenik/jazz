package net.bodz.bas.util;

import net.bodz.bas.potato.mda.tagbook.ArtifactDoc;
import net.bodz.mda.xjdoc.conv.ClassDocs;

public class AbstractPlugin
        implements IPlugin {

    ArtifactDoc artifactDoc;

    public AbstractPlugin() {
        artifactDoc = ClassDocs.loadFromResource(getClass()).as(ArtifactDoc.class);
    }

    @Override
    public String getDescription() {
        String description = artifactDoc.getTextHeader();
        return description;
    }

    @Deprecated
    @Override
    public void initialize() {
    }

}
