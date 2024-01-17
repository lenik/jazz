package net.bodz.violet.art.dao;

import net.bodz.lily.t.base.CoMessageCriteriaBuilder;

public class _ArtifactDocCriteriaBuilder_stuff<self_t extends _ArtifactDocCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final IntegerField artifactId = integer("art");

}
