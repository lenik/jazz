package net.bodz.violet.schema.art;

import javax.persistence.Table;

/**
 * 物品标签
 */
@Table(schema = "violet", name = "arttag")
public class ArtifactTag
        extends _ArtifactTag_stuff<ArtifactTag> {

    private static final long serialVersionUID = 1L;

}
