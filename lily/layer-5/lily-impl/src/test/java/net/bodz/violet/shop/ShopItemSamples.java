package net.bodz.violet.shop;

import net.bodz.lily.test.TestSamples;
import net.bodz.violet.art.Artifact;

public class ShopItemSamples
        extends TestSamples {

    public static ShopItem build(Shop shop, ShopItemCategory category, Artifact artifact) {
        ShopItem a = new ShopItem();
        a.setLabel("shopItem-1");
        a.setDescription("A shopItem named shopItem-1.");
        a.setShop(shop);
        a.setCategory(category);
        a.setArtifact(artifact);
        a.setQuantity(random.nextInt(10000) / 100.0);
        a.setPrice(random.nextInt(10000) / 100.0);
        return a;
    }

}
