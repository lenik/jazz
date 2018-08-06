package net.bodz.violet.store;

import org.joda.time.DateTime;

import net.bodz.lily.test.TestSamples;
import net.bodz.violet.art.Artifact;

public class StoreOrderItemSamples
        extends TestSamples {

    Artifact artifact;
    Region region;

    String batch;
    String divs;
    Long serial;
    DateTime expire;

    public static StoreOrderItem build(StoreOrder order, Artifact artifact, Region region) {
        StoreOrderItem a = new StoreOrderItem();
        a.setLabel("storeOrderItem-1");
        a.setDescription("A storeOrderItem named storeOrderItem-1.");
        a.setOrder(order);
        a.setArtifact(artifact);
        a.setRegion(region);

        a.setBatch("batch1");
        a.setDivs("divs1");

        a.setSerial(random.nextLong());
        a.setPrice(random.nextInt(10000) / 100.0);
        a.setQuantity(random.nextInt(100));
        return a;
    }

}
