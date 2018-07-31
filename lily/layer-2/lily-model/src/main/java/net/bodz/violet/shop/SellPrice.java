package net.bodz.violet.shop;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.violet.art.Artifact;

/**
 * 物品销售价格
 */
@IdType(Long.class)
@Table(name = "art_price")
public class SellPrice
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    Artifact artifact;
    BigDecimal price = BigDecimal.ZERO;

    public SellPrice() {
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(double price) {
        BigDecimal d = new BigDecimal(price);
        setPrice(d);
    }

    public void setPrice(BigDecimal price) {
        if (price == null)
            throw new NullPointerException("price");
        this.price = price;
    }

}
