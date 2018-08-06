package net.bodz.violet.tran;

import net.bodz.lily.test.TestSamples;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.shop.SalesOrder;
import net.bodz.violet.shop.SalesOrderItem;

public class TransportOrderItemSamples
        extends TestSamples {

    public static TransportOrderItem build(TransportOrder order, SalesOrder salesOrder, SalesOrderItem salesOrderItem,
            Artifact artifact) {
        TransportOrderItem a = new TransportOrderItem();
        a.setLabel("transportOrderItem-1");
        a.setDescription("A transportOrderItem named transportOrderItem-1.");

        a.setOrder(order);
        a.setSalesOrder(salesOrder);
        a.setSalesOrderItem(salesOrderItem);
        a.setArtifact(artifact);

        a.setPrice(random.nextInt(10000) / 100.0);
        a.setQuantity(random.nextInt(100));
        return a;
    }

}
