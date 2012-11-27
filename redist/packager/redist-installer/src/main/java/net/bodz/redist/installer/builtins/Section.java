package net.bodz.redist.installer.builtins;

import net.bodz.mda.xjdoc.model1.ArtifactDoc;
import net.bodz.redist.installer.AbstractComponent;
import net.bodz.redist.installer.IComponent;
import net.bodz.redist.installer.ISession;

public class Section
        extends AbstractComponent {

    public Section(String name, int flags, ArtifactDoc artifactDoc, IComponent... children) {
        super(flags, artifactDoc);
        setName(name);
        for (IComponent child : children)
            add(child);
    }

    @Override
    public CJob install(ISession session) {
        return null;
    }

}
