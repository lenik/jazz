package net.bodz.violet.shop;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.art.Artifact;

public class SalesOrderItemSamples
        extends TestSampleBuilder {

    public SalesOrder odr;
    public ShopItem shopItem;
    public Artifact artifact;

    public SalesOrderItem build()
            throws Exception {
        SalesOrderItem a = new SalesOrderItem();
        a.setOdr(odr);
        a.setArtifact(artifact);
        a.setId(9028915288260000461L);
        a.setBeginTime(new Timestamp(Dates.ISO8601Z.parse("2022-12-29T03:18:49.411+0800").getTime()));
        a.setEndTime(new Timestamp(Dates.ISO8601Z.parse("2023-01-02T05:36:16.593+0800").getTime()));
        a.setYear(467276462);
        a.setQuantity(new BigDecimal("69968999917107"));
        a.setPrice(new BigDecimal("1344269"));
        a.setAmount(new BigDecimal("2503"));
        a.setN1(new BigDecimal("1.08"));
        return a;
    }

}
