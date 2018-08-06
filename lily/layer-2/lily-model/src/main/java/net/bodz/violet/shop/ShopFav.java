package net.bodz.violet.shop;

import javax.persistence.Table;

import net.bodz.lily.template.FavRecord;

@Table(name = "shop_fav")
public class ShopFav
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    Shop shop;

    public ShopFav() {
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

}
