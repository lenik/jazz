package net.bodz.violet.schema.shop;

import java.math.BigDecimal;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.shop.dao.ShopItemCategoryMapper;
import net.bodz.violet.schema.shop.dao.ShopMapper;

public class ShopItemSamples
        extends TestSampleBuilder {

    public ShopItemCategory category;
    public Shop shop;
    public Artifact artifact;

    @Override
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

    @Override
    public ShopItemSamples wireAny(IRandomPicker picker) {
        this.category = picker.pickAny(ShopItemCategoryMapper.class, "shopitemcat");
        this.shop = picker.pickAny(ShopMapper.class, "shop");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        return this;
    }

    @Override
    public ShopItem buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
