package net.bodz.violet.store.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.store.ArtifactCategory;

/**
 * 物料分类
 * 
 * @label 物料分类
 * 
 * @rel art/: 管理物料
 */
@ObjectType(ArtifactCategory.class)
public class ArtifactCategoryIndex
        extends CoIndex<ArtifactCategory, ArtifactCategoryMask> {

}
