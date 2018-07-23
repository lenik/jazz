package net.bodz.violet.asset.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.violet.asset.Asset;
import net.bodz.violet.asset.impl.AssetMask;

/**
 * @mapper.xml AssetMapper.xml
 */
public interface AssetMapper
        extends IMapperTemplate<Asset, AssetMask> {

}
