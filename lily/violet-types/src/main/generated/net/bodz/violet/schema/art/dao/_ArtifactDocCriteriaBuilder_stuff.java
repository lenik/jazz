package net.bodz.violet.schema.art.dao;

import net.bodz.lily.concrete.CoMessageCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.art.ArtifactDoc;

@ForEntityType(ArtifactDoc.class)
public class _ArtifactDocCriteriaBuilder_stuff<self_t extends _ArtifactDocCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

    public final StringField formArguments = string("formargs");

    public final IntegerField artifactId = integer("art");

}
