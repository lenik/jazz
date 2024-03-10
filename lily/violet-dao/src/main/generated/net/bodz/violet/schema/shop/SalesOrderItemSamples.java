package net.bodz.violet.schema.shop;

import java.math.BigDecimal;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.shop.dao.SalesOrderMapper;
import net.bodz.violet.schema.shop.dao.ShopItemMapper;

public class SalesOrderItemSamples
        extends TestSampleBuilder {

    public SalesOrder order;
    public ShopItem shopItem;
    public Artifact artifact;

    @Override
    public SalesOrderItem build()
            throws Exception {
        SalesOrderItem a = new SalesOrderItem();
        a.setOrder(order);
        a.setShopItem(shopItem);
        a.setArtifact(artifact);
        a.setQuantity(new BigDecimal("641961"));
        a.setPrice(new BigDecimal("99689999171071"));
        a.setAmount(new BigDecimal("3442"));
        a.setN1(new BigDecimal("7025030013"));
        return a;
    }

    @Override
    public SalesOrderItemSamples wireAny(IRandomPicker picker) {
        this.order = picker.pickAny(SalesOrderMapper.class, "saleodr");
        this.shopItem = picker.pickAny(ShopItemMapper.class, "shopitem");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        return this;
    }

    @Override
    public SalesOrderItem buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
