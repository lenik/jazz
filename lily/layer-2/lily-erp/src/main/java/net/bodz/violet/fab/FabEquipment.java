package net.bodz.violet.fab;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.violet.asset.Asset;

@IdType(Integer.class)
public class FabEquipment
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    Asset asset;

    public FabEquipment() {
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

}
