package net.bodz.violet.shop;

import java.math.BigDecimal;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.art.Artifact;

public class ShopItemSamples
        extends TestSampleBuilder {

    public ShopItemCategory category;
    public Shop shop;
    public Artifact artifact;

    public ShopItem build()
            throws Exception {
        ShopItem a = new ShopItem();
        a.setCategory(category);
        a.setShop(shop);
        a.setArtifact(artifact);
        a.setPrice(new BigDecimal("3.93"));
        a.setQuantity(new BigDecimal(".76"));
        return a;
    }

}
