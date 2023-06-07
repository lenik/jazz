package net.bodz.violet.shop;

import java.math.BigDecimal;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class CartItemSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public ShopItem shopItem;
    public User ownerUser;

    public CartItem build()
            throws Exception {
        CartItem a = new CartItem();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setId(6488351498676160163L);
        a.setPrice(new BigDecimal("24905165748786448"));
        a.setQuantity(new BigDecimal("39658"));
        return a;
    }

}
