package net.bodz.violet.shop;

import net.bodz.lily.test.TestSamples;
import net.bodz.violet.art.Artifact;

public class SalesOrderItemSamples
        extends TestSamples {

    public static SalesOrderItem build(SalesOrder order, ShopItem shopItem, Artifact artifact) {
        SalesOrderItem a = new SalesOrderItem();
        a.setLabel("salesOrderItem-1");
        a.setDescription("A salesOrderItem named salesOrderItem-1.");
        a.setOrder(order);
        a.setShopItem(shopItem);
        a.setArtifact(artifact);

        if (random.nextBoolean()) {
            a.setResale(true);
            a.setAltLabel("alter label");
            a.setAltSpec("alter spec");
        }

        a.setPrice(random.nextInt(10000) / 100.0);
        a.setQuantity(random.nextInt(100));
        return a;
    }

}
