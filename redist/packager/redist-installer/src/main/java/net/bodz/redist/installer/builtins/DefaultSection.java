package net.bodz.redist.installer.builtins;

import net.bodz.mda.xjdoc.model1.ArtifactDoc;
import net.bodz.redist.installer.IComponent;

public class DefaultSection
        extends Section {

    public DefaultSection(String name, int flags, ArtifactDoc artifactDoc, IComponent... children) {
        super(name, flags | VISIBLE | SELECTED, artifactDoc, children);
    }

}
