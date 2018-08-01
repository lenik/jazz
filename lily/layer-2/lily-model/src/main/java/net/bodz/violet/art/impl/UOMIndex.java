package net.bodz.violet.art.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.art.UOM;

/**
 * 定义度量中使用的单位。
 *
 * @label 度量单位
 *
 * @rel art/: 管理物料
 */
@ObjectType(UOM.class)
public class UOMIndex
        extends CoIndex<UOM, UOMMask> {

}
