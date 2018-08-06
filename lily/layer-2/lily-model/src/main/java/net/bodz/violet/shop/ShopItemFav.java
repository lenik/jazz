package net.bodz.violet.shop;

import javax.persistence.Table;

import net.bodz.lily.template.FavRecord;

@Table(name = "shopitem_fav")
public class ShopItemFav
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    ShopItem shopItem;

    public ShopItemFav() {
    }

    public ShopItem getShopItem() {
        return shopItem;
    }

    public void setShopItem(ShopItem shopItem) {
        this.shopItem = shopItem;
    }

}
