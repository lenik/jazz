package net.bodz.violet.store.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.QVariantMap;
import net.bodz.lily.model.base.CoObjectMask;

public class ArtifactMask
        extends CoObjectMask {

    public Integer categoryId;
    public String skuCode_;
    public String barCode_;

    public Integer uomId;
    public String modelName;

    public boolean noCategory;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isNoCategory() {
        return noCategory;
    }

    public void setNoCategory(boolean noCategory) {
        this.noCategory = noCategory;
    }

    public String getSkuCodePrefix() {
        return skuCode_;
    }

    public void setSkuCode_(String skuCodePrefix) {
        this.skuCode_ = skuCodePrefix;
    }

    public String getBarCodePrefix() {
        return barCode_;
    }

    public void setBarCodePrefix(String barCodePrefix) {
        this.barCode_ = barCodePrefix;
    }

    public Integer getUomId() {
        return uomId;
    }

    public void setUomId(Integer uomId) {
        this.uomId = uomId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public void readObject(IVariantMap<String> _map)
            throws ParseException {
        super.readObject(_map);

        QVariantMap<String> map = QVariantMap.from(_map);
        categoryId = map.getInt("cat", categoryId);
        skuCode_ = map.getString("sku", skuCode_);
        barCode_ = map.getString("bar", barCode_);
        uomId = map.getInt("uom", uomId);
        modelName = map.getString("spec", modelName);
    }

}
