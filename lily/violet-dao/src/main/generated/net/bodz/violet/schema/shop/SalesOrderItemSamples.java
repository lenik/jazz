package net.bodz.violet.schema.shop;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import net.bodz.bas.c.java.util.DateTimes;
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
        a.setId(9028915288260000461L);
        a.setBeginTime(ZonedDateTime.parse("2023-12-28 17:18:49", DateTimes.D10T8));
        a.setEndTime(ZonedDateTime.parse("2024-01-17 06:27:11", DateTimes.D10T8));
        a.setYear(467276462);
        a.setQuantity(new BigDecimal("69968999917107"));
        a.setPrice(new BigDecimal("1344269"));
        a.setAmount(new BigDecimal("2503"));
        a.setN1(new BigDecimal("1.08"));
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
