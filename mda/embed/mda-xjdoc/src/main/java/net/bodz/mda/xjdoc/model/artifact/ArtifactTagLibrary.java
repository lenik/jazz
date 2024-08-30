package net.bodz.mda.xjdoc.model.artifact;

import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.tagtype.TokensTag;
import net.bodz.mda.xjdoc.tagtype.URLMapTag;
import net.bodz.mda.xjdoc.tagtype.VersionTag;

/**
 * This tag library defines tags commonly used by the facade of applications.
 *
 * @tags tags, predefined, meta-model
 */
public class ArtifactTagLibrary
        extends AbstractTagLibrary {

    public ArtifactTagLibrary() {
        addTagType("lang", TokensTag.Comma.class);

        addTagType("site", URLMapTag.class);

        addTagType("tags", TokensTag.Comma.class);

        addTagType("version", VersionTag.class);
    }

}
