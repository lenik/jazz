package net.bodz.violet.art;

import java.math.BigDecimal;

public class FabCost {

    BigDecimal laborWork;
    BigDecimal power;
    BigDecimal water;
    BigDecimal equipment;
    BigDecimal consumable;
    BigDecimal other;

    public BigDecimal getLaborWork() {
        return laborWork;
    }

    public void setLaborWork(BigDecimal laborWork) {
        this.laborWork = laborWork;
    }

    public void setLaborWork(double laborWork) {
        this.laborWork = BigDecimal.valueOf(laborWork);
    }

    public BigDecimal getPower() {
        return power;
    }

    public void setPower(BigDecimal power) {
        this.power = power;
    }

    public void setPower(double power) {
        this.power = BigDecimal.valueOf(power);
    }

    public BigDecimal getWater() {
        return water;
    }

    public void setWater(BigDecimal water) {
        this.water = water;
    }

    public void setWater(double water) {
        this.water = BigDecimal.valueOf(water);
    }

    public BigDecimal getEquipment() {
        return equipment;
    }

    public void setEquipment(BigDecimal equipment) {
        this.equipment = equipment;
    }

    public void setEquipment(double equipment) {
        this.equipment = BigDecimal.valueOf(equipment);
    }

    public BigDecimal getConsumable() {
        return consumable;
    }

    public void setConsumable(BigDecimal consumable) {
        this.consumable = consumable;
    }

    public void setConsumable(double consumable) {
        this.consumable = BigDecimal.valueOf(consumable);
    }

    public BigDecimal getOther() {
        return other;
    }

    public void setOther(BigDecimal other) {
        this.other = other;
    }

    public void setOther(double other) {
        this.other = BigDecimal.valueOf(other);
    }

}
