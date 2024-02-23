package net.bodz.violet.schema.art;

import net.bodz.lily.concrete.IAttrInProps;

public interface IArtifactExtrasInProps
        extends
            IAttrInProps,
            IArtifactExtras {

    String K_CAUTION = "caution";
    String K_COLOR = "color";
    String K_SUPPLY_DELAY = "supply.delay";

    @Override
    default String getCaution() {
        return getAttribute(K_CAUTION).getString();
    }

    @Override
    default void setCaution(String caution) {
        setAttribute(K_CAUTION, caution);
    }

    @Override
    default String getColor() {
        return getAttribute(K_COLOR).getString();
    }

    @Override
    default void setColor(String color) {
        setAttribute(K_COLOR, color);
    }

    @Override
    default int getSupplyDelay() {
        return getAttribute(K_SUPPLY_DELAY).getInt(0);
    }

    @Override
    default void setSupplyDelay(int supplyDelay) {
        setAttribute(K_SUPPLY_DELAY, supplyDelay);
    }

}
