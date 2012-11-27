package net.bodz.redist.installer.builtins;

import net.bodz.mda.xjdoc.model1.ArtifactDoc;
import net.bodz.redist.installer.IComponent;

public class HiddenSection
        extends Section {

    public HiddenSection(String name, int flags, ArtifactDoc artifactDoc, IComponent... children) {
        super(name, flags | SELECTED, artifactDoc, children);
    }

}
