package net.bodz.violet.schema.art;

import javax.persistence.Table;

/**
 * 物品分类
 */
@Table(schema = "violet", name = "artcat")
public class ArtifactCategory
        extends _ArtifactCategory_stuff<ArtifactCategory> {

    private static final long serialVersionUID = 1L;

}
