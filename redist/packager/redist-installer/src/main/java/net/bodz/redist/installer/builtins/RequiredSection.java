package net.bodz.redist.installer.builtins;

import net.bodz.mda.xjdoc.model1.ArtifactDoc;
import net.bodz.redist.installer.IComponent;

public class RequiredSection
        extends Section {

    public RequiredSection(String name, int flags, ArtifactDoc artifactDoc, IComponent... children) {
        super(name, flags | VISIBLE | READ_ONLY | SELECTED, artifactDoc, children);
    }

}
