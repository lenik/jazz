package net.bodz.violet.manu;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.violet.asset.Asset;

@IdType(Integer.class)
public class ManuEquipment
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    Asset asset;

    public ManuEquipment() {
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

}
