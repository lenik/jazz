package net.bodz.violet.shop;

import net.bodz.lily.test.TestSamples;

public class CartItemSamples
        extends TestSamples {

    public static CartItem build(ShopItem shopItem) {
        CartItem a = new CartItem();
        a.setLabel("cartItem-1");
        a.setDescription("A cartItem named cartItem-1.");
        a.setShopItem(shopItem);
        a.setQuantity(random.nextInt(100));
        a.setPrice(random.nextInt(10000) / 100.0);
        return a;
    }

}
