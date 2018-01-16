package net.bodz.violet.art.impl;

import net.bodz.lily.model.base.CoObjectMask;

public class ArtifactMask
        extends CoObjectMask {

    public Integer categoryId;
    public Integer phaseId;
    public String skuCode_;
    public String barCode_;

    public Integer uomId;
    public String modelName;

    public boolean noCategory;
    public boolean noPhase;

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

    public Integer getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Integer phaseId) {
        this.phaseId = phaseId;
    }

    public boolean isNoPhase() {
        return noPhase;
    }

    public void setNoPhase(boolean noPhase) {
        this.noPhase = noPhase;
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

}
