package net.bodz.redist.installer.builtins;

import net.bodz.mda.xjdoc.model1.ArtifactDoc;
import net.bodz.redist.installer.IComponent;

public class OptionalSection
        extends Section {

    public OptionalSection(String name, int flags, ArtifactDoc artifactDoc, IComponent... children) {
        super(name, flags | VISIBLE, artifactDoc, children);
    }

}
