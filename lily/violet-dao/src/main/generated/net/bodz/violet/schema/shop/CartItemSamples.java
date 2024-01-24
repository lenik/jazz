package net.bodz.violet.schema.shop;

import java.math.BigDecimal;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.shop.dao.ShopItemMapper;

public class CartItemSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public ShopItem shopItem;
    public User ownerUser;

    @Override
    public CartItem build()
            throws Exception {
        CartItem a = new CartItem();
        a.setOwnerGroup(ownerGroup);
        a.setShopItem(shopItem);
        a.setOwnerUser(ownerUser);
        a.setId(6488351498676160163L);
        a.setPrice(new BigDecimal("24905165748786448"));
        a.setQuantity(new BigDecimal("39658"));
        return a;
    }

    @Override
    public CartItemSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.shopItem = picker.pickAny(ShopItemMapper.class, "shopitem");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public CartItem buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
