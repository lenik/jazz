package net.bodz.violet.art;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

/**
 * 物品标签
 */
@IdType(Integer.class)
@Table(name = "arttag")
public class ArtifactTag
        extends CoNode<ArtifactTag, Integer> {

    private static final long serialVersionUID = 1L;

}
