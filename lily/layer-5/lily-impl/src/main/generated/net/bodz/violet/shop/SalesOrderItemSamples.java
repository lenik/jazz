package net.bodz.violet.shop;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.shop.dao.SalesOrderMapper;
import net.bodz.violet.shop.dao.ShopItemMapper;

public class SalesOrderItemSamples
        extends TestSampleBuilder {

    public SalesOrder odr;
    public ShopItem shopItem;
    public Artifact artifact;

    @Override
    public SalesOrderItem build()
            throws Exception {
        SalesOrderItem a = new SalesOrderItem();
        a.setOdr(odr);
        a.setShopItem(shopItem);
        a.setArtifact(artifact);
        a.setId(9028915288260000461L);
        a.setBeginTime(new DateTime(Dates.ISO8601Z.parse("2023-12-29T03:18:49.411+0800").getTime()));
        a.setEndTime(new DateTime(Dates.ISO8601Z.parse("2024-01-02T05:36:16.593+0800").getTime()));
        a.setYear(467276462);
        a.setQuantity(new BigDecimal("69968999917107"));
        a.setPrice(new BigDecimal("1344269"));
        a.setAmount(new BigDecimal("2503"));
        a.setN1(new BigDecimal("1.08"));
        return a;
    }

    @Override
    public SalesOrderItemSamples wireAny(IRandomPicker picker) {
        this.odr = picker.pickAny(SalesOrderMapper.class, "saleodr");
        this.shopItem = picker.pickAny(ShopItemMapper.class, "shopitem");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        return this;
    }

    @Override
    public SalesOrderItem buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
